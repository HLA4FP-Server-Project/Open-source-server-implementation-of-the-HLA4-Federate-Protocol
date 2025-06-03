package com.rug.tno;

import com.rug.tno.layers.HlaForwardingLayer;
import com.rug.tno.session.SessionManager;
import hla.rti1516_202X.RTIambassador;
import hla.rti1516_202X.encoding.EncoderFactory;
import io.netty.channel.embedded.EmbeddedChannel;

public class CommonTestUtil {
    public static EmbeddedChannel setupTestPipeline(RTIambassador ambassador, EncoderFactory encoder) {
        var channel = new EmbeddedChannel();
        var sessionManager = new SessionManager();
        var forwarder = new HlaForwardingLayer("localhost", ambassador, encoder);
        App.configurePipeline(channel.pipeline(), sessionManager, forwarder);

        return channel;
    }
}
