package com.rug.tno.layers;

import com.rug.tno.fpdata.*;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

public class FpSessionLayer extends MessageToMessageCodec<FpMessageFrame,FpPayload> {
    private long lastSeenSequenceId = 0;
    private long sequenceNumber = 0;
    private long sessionId = 0;

    @Override
    protected void encode(ChannelHandlerContext ctx, FpPayload payload, List<Object> list) throws Exception {
        list.add(new FpMessageFrame(
                sequenceNumber++,
                sessionId,
                lastSeenSequenceId,
                payload
        ));
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, FpMessageFrame message, List<Object> list) throws Exception {
        this.lastSeenSequenceId = message.sequenceNumber();
        var payload = message.payload();

        // TODO handle reconnections
        if (payload instanceof CtrlNewSession) {
            this.sessionId = 123;
            ctx.channel().writeAndFlush(new CtrlNewSessionStatus(CtrlNewSessionStatus.Status.SUCCESS));
        } else if (payload instanceof CtrlHeartbeat) {
            // We must respond to heartbeats
            ctx.channel().writeAndFlush(new CtrlHeartbeatResponse(message.sequenceNumber()));
        } else {
            // messages unrelated to session management are forwarded to the next layer
            list.add(new FpMessage(
                    message.sequenceNumber(),
                    message.payload()
            ));
        }
    }
}
