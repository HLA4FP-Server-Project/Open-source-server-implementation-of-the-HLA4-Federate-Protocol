package com.rug.tno;

import com.rug.tno.layers.HlaForwardingLayer;
import com.rug.tno.session.SessionManager;
import hla.rti1516_202X.RTIambassador;
import hla.rti1516_202X.encoding.EncoderFactory;
import io.netty.channel.embedded.EmbeddedChannel;
import org.mockito.Answers;
import org.mockito.Mockito;

public class CommonTestUtil {
    /**
     * Sets up a virtual server, which behaves like the real thing but uses a mocked rti.
     */
    public static TestServer setupTestServer() {
        var channel = new EmbeddedChannel();
        var sessionManager = new SessionManager();
        var mockRtiAmbassador = Mockito.mock(RTIambassador.class, Answers.RETURNS_DEEP_STUBS);
        var mockEncoder = Mockito.mock(EncoderFactory.class);

        // Initialize the app whilst mocking the rti
        var forwarder = new HlaForwardingLayer("localhost", mockRtiAmbassador, mockEncoder);
        App.configurePipeline(channel.pipeline(), sessionManager, forwarder);

        // Return all the objects which are useful for testing the server
        return new TestServer(
                channel,
                sessionManager,
                mockRtiAmbassador,
                mockEncoder
        );
    }

    public record TestServer(EmbeddedChannel channel, SessionManager sessionManager, RTIambassador mockAmbassador, EncoderFactory mockEncoder) {}
}
