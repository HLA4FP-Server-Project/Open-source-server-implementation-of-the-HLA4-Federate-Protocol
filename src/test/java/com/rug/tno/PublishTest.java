package com.rug.tno;

import com.google.protobuf.ByteString;
import com.rug.tno.layers.FpSessionLayer;
import hla.rti1516_2024.fedpro.*;
import hla.rti1516_202X.exceptions.*;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * R3-F-M
 * Tests if a publication message gets forwarded correctly
 */
public class PublishTest {
    @Test
    public void forwardPublishInteractionClassRequest() throws FederateNotExecutionMember, NotConnected, RTIinternalError, CouldNotDecode, RestoreInProgress, InteractionClassNotDefined, FederateServiceInvocationsAreBeingReportedViaMOM, SaveInProgress {
        var testServer = CommonTestUtil.setupTestServer();

        // Setup
        // Skip setting up a session
        var testSession = testServer.sessionManager().createNewSession();
        FpSessionLayer.setSessionForChannel(testServer.channel(), testSession);
        var testClassHandle = new byte[]{1,2,3,4}; // the RTI refers to class handles using some arbitrary bytes

        // Send HLA Call request
        var hlaCallRequest = Unpooled.buffer();
        hlaCallRequest.writeInt(0); // Sequence number
        hlaCallRequest.writeLong(testSession.id()); // Session id
        hlaCallRequest.writeInt(0); // Last received message id
        hlaCallRequest.writeInt(20); // Type (hla call request)
        hlaCallRequest.writeBytes(CallRequest.newBuilder()
                        .setPublishInteractionClassRequest(PublishInteractionClassRequest.newBuilder()
                                .setInteractionClass(InteractionClassHandle.newBuilder()
                                        .setData(ByteString.copyFrom(testClassHandle))
                                        .build())
                                .build())
                .build().toByteArray());
        hlaCallRequest = prependLength(hlaCallRequest); // To make it a valid request
        testServer.channel().writeInbound(hlaCallRequest);

        // Verify
        Mockito.verify(testServer.mockAmbassador().getInteractionClassHandleFactory()).decode(testClassHandle, 0);
        Mockito.verify(testServer.mockAmbassador()).publishInteractionClass(Mockito.any());
    }

    private static ByteBuf prependLength(ByteBuf buf) {
        var newBuf = Unpooled.buffer();
        newBuf.writeInt(buf.writerIndex() + 4); // 4, to account for the 4 bytes we're adding
        newBuf.writeBytes(buf);
        return newBuf;
    }
}
