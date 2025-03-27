package com.rug.tno;

import com.rug.tno.connection.TcpConnection;
import com.rug.tno.layers.*;
import com.rug.tno.server.Server;
import hla.rti1516_202X.exceptions.RTIinternalError;

public class App {

    public static void main(String[] args) throws Exception {
        var port = 15164;
        if (args.length > 0){
            port = Integer.parseInt(args[0]);
        }

        var handler = new TcpConnection(pipeline -> {
            try {
                pipeline.addLast(
                        new FpPacketDecoder(),
                        new FpPacketEncoder(),
                        new MessageParser(),
                        new DebugPrintLayer(),
                        new FpSessionLayer(),
                        new HlaForwardingLayer("localhost")
                );
            } catch (RTIinternalError e) {
                throw new RuntimeException(e);
            }
        });
        new Server(port, handler).run();
    }
}
