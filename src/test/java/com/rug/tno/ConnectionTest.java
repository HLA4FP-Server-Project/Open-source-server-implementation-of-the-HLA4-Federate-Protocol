package com.rug.tno;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.rug.tno.layers.*;
import com.rug.tno.session.SessionManager;
import hla.rti1516_202X.RTIambassador;
import hla.rti1516_202X.encoding.EncoderFactory;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

/**
 * R1-F-M
 * Tests if the server properly responds to a federate connecting, in accordance to the spec.
 */
public class ConnectionTest {
    private static final byte[] NEW_SESSION_PACKET = new byte[] {
            // Size
            0x00,
            0x00,
            0x00,
            28,
            // Sequence number
            0x00,
            0x00,
            0x00,
            0x00,
            // Session ID (0 for new session packets)
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            // Last received message number
            (byte)0xFF,
            (byte)0xFF,
            (byte)0xFF,
            (byte)0xFF,
            // Message type,
            0x00,
            0x00,
            0x00,
            0x01,
            // Payload (HLA version)
            0x00,
            0x00,
            0x00,
            0x01
    };
    private static final byte[] EXPECTED_RESPONSE = new byte[] {
            // Size
            0x00,
            0x00,
            0x00,
            28,
            // Sequence number
            0x00,
            0x00,
            0x00,
            0x00,
            // Session ID (doesn't matter, ignored)
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            // Last received message number
            (byte)0xFF,
            (byte)0xFF,
            (byte)0xFF,
            (byte)0xFF,
            // Message type,
            0x00,
            0x00,
            0x00,
            0x02,
            // Payload (new session success)
            0x00,
            0x00,
            0x00,
            0x00
    };

    @Test
    public void testNewSession() {
        var mockRtiAmbassador = Mockito.mock(RTIambassador.class);
        var mockEncoder = Mockito.mock(EncoderFactory.class);
        var channel = CommonTestUtil.setupTestPipeline(mockRtiAmbassador, mockEncoder);

        // Send a new session packet
        channel.writeInbound(Unpooled.copiedBuffer(NEW_SESSION_PACKET));
        var response = channel.readOutbound();

        // Check if the response was correct, whilst blanking
        // out the session id, because it's randomly generated
        var responseBytes = ByteBufUtil.getBytes((ByteBuf)response);
        Arrays.fill(responseBytes, 8, 16, (byte)0);
        Assertions.assertArrayEquals(EXPECTED_RESPONSE, responseBytes);
    }
}
