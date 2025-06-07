package com.rug.tno;

import com.rug.tno.connection.TcpConnection;
import com.rug.tno.layers.*;
import com.rug.tno.server.Server;
import com.rug.tno.session.SessionManager;
import hla.rti1516_202X.exceptions.RTIinternalError;
import io.netty.channel.ChannelPipeline;

public class App {

    public static void main(String[] args) throws Exception {
        var port = 15164;
        if (args.length > 0){
            port = Integer.parseInt(args[0]);
        }

        var sessionManager = new SessionManager();

        var handler = new TcpConnection(pipeline -> {
            try {
                var forwarder = new HlaForwardingLayer("localhost");
                configurePipeline(pipeline, sessionManager, forwarder);
            } catch (RTIinternalError e) {
                throw new RuntimeException(e);
            }
        });
        new Server(port, handler).run();
    }

    public static void configurePipeline(ChannelPipeline pipeline, SessionManager sessionManager, HlaForwardingLayer forwardingLayer) {
        pipeline.addLast(
                new FpPacketDecoder(),
                new FpPacketEncoder(),
                new FpPayloadCodec(),
                new DebugPrintLayer(),
                new FpValidationLayer(),
                new FpSessionLayer(sessionManager),
                forwardingLayer
        );
    }
}
