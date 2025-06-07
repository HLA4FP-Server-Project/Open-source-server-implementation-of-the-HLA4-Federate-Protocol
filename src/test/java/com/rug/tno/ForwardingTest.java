package com.rug.tno;

import com.google.protobuf.ByteString;
import com.rug.tno.layers.FpSessionLayer;
import hla.rti1516_2024.fedpro.CallRequest;
import hla.rti1516_2024.fedpro.GetAttributeHandleRequest;
import hla.rti1516_2024.fedpro.ObjectClassHandle;
import hla.rti1516_202X.exceptions.*;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * R4-F-M
 * Tests sending a basic message through the pipeline, and checks if it's forwarded to the appropriate hla ambassador
 * method
 */
public class ForwardingTest {
    @Test
    public void forwardGetAttributeHandleRequest() throws FederateNotExecutionMember, NameNotFound, NotConnected, InvalidObjectClassHandle, RTIinternalError {
        var testServer = CommonTestUtil.setupTestServer();

        // Setup
        // Skip setting up a session
        var testSession = testServer.sessionManager().createNewSession();
        FpSessionLayer.setSessionForChannel(testServer.channel(), testSession);

        // Send HLA Call request
        var hlaCallRequest = Unpooled.buffer();
        hlaCallRequest.writeInt(0); // Sequence number
        hlaCallRequest.writeLong(testSession.id()); // Session id
        hlaCallRequest.writeInt(0); // Last received message id
        hlaCallRequest.writeInt(20); // Type (hla call request)
        hlaCallRequest.writeBytes(CallRequest.newBuilder()
                        .setGetAttributeHandleRequest(GetAttributeHandleRequest.newBuilder()
                                .setAttributeName("testName")
                                .setObjectClass(ObjectClassHandle.newBuilder()
                                        .setData(ByteString.copyFrom(new byte[]{1,2,3,4}))
                                        .build())
                                .build())
                .build().toByteArray());
        hlaCallRequest = prependLength(hlaCallRequest); // To make it a valid request
        testServer.channel().writeInbound(hlaCallRequest);

        // Verify
        Mockito.verify(testServer.mockAmbassador()).getAttributeHandle(Mockito.any(), Mockito.eq("testName"));
    }

    private static ByteBuf prependLength(ByteBuf buf) {
        var newBuf = Unpooled.buffer();
        newBuf.writeInt(buf.writerIndex() + 4); // 4, to account for the 4 bytes we're adding
        newBuf.writeBytes(buf);
        return newBuf;
    }
}
