package com.rug.tno;

import com.google.protobuf.InvalidProtocolBufferException;
import com.rug.tno.layers.FpSessionLayer;
import hla.rti1516_2024.fedpro.*;
import hla.rti1516_202X.RtiConfiguration;
import hla.rti1516_202X.exceptions.*;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * R7-F-M
 * Tests sending a basic message through the pipeline, but mocks the RTI to throw an error. It tests if the error is
 * reported correctly
 */
public class ErrorTest {
    @Test
    public void rtiThrowingException() throws RTIinternalError, CallNotAllowedFromWithinCallback, AlreadyConnected, Unauthorized, ConnectionFailed, UnsupportedCallbackModel, InvalidProtocolBufferException {
        var testServer = CommonTestUtil.setupTestServer();

        // Setup
        // Skip setting up a session
        var testSession = testServer.sessionManager().createNewSession();
        FpSessionLayer.setSessionForChannel(testServer.channel(), testSession);
        // Make the rti ambassador throw errors
        Mockito
                .when(testServer.mockAmbassador().connect(Mockito.any(), Mockito.any(), Mockito.any(RtiConfiguration.class)))
                .thenThrow(new RTIinternalError("Exception thrown to test exception handling"));

        // Send HLA Call request
        var hlaCallRequest = Unpooled.buffer();
        hlaCallRequest.writeInt(0); // Sequence number
        hlaCallRequest.writeLong(testSession.id()); // Session id
        hlaCallRequest.writeInt(0); // Last received message id
        hlaCallRequest.writeInt(20); // Type (hla call request)
        hlaCallRequest.writeBytes(CallRequest.newBuilder() // Send a basic HLA connect request
                        .setConnectRequest(ConnectRequest.newBuilder().build())
                .build().toByteArray());
        hlaCallRequest = prependLength(hlaCallRequest); // To make it a valid request
        testServer.channel().writeInbound(hlaCallRequest);

        // Verify
        var response = (ByteBuf)testServer.channel().readOutbound();
        response.skipBytes(20); // Skip irrelevant part of response
        Assertions.assertEquals(21, response.readInt()); // The server should have returned a hla call response
        Assertions.assertEquals(0, response.readInt()); // It should be a response to our message
        var payload = CallResponse.parseFrom(ByteBufUtil.getBytes(response));
        // Assert that the payload contains the expected exception
        Assertions.assertEquals(CallResponse.newBuilder()
                .setExceptionData(ExceptionData.newBuilder()
                        .setExceptionName("RTIinternalError")
                        .setDetails("Exception thrown to test exception handling")
                        .build())
                .build(),
                payload);
    }

    private static ByteBuf prependLength(ByteBuf buf) {
        var newBuf = Unpooled.buffer();
        newBuf.writeInt(buf.writerIndex() + 4); // 4, to account for the 4 bytes we're adding
        newBuf.writeBytes(buf);
        return newBuf;
    }
}
