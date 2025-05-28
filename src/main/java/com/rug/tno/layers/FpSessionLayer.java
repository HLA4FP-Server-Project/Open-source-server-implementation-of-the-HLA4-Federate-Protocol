package com.rug.tno.layers;

import com.rug.tno.fpdata.*;
import com.rug.tno.session.SessionInfo;
import com.rug.tno.session.SessionManager;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.util.AttributeKey;

import java.util.List;

public class FpSessionLayer extends MessageToMessageCodec<FpMessageFrame,FpPayload> {
    /**
     * A key for use with Netty. You can use this key to retrieve/store the session which is attached
     * to a channel
     */
    public static final AttributeKey<SessionInfo> CHANNEL_SESSION = AttributeKey.valueOf("channel_session");
    private final SessionManager sessionMngr;

    // TODO these need to be moved to the session info:
    private long lastSeenSequenceId = 0;
    private long sequenceNumber = 0;

    public FpSessionLayer(SessionManager sessionMngr) {
        this.sessionMngr = sessionMngr;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, FpPayload payload, List<Object> list) throws Exception {
        var session = ctx.channel().attr(CHANNEL_SESSION).get();
        var sessionId = session == null ? 0 : session.id();

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
            onNewSession(ctx.channel());
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

    private void onNewSession(Channel channel) {
        try {
            if (channel.attr(CHANNEL_SESSION).get() != null) {
                // Can't create a session if the channel is already associated with one
                channel.writeAndFlush(new CtrlNewSessionStatus(CtrlNewSessionStatus.Status.FAILURE_OTHER));
            } else {
                // Create a new session and associate it with this channel
                var session = sessionMngr.createNewSession();
                channel.attr(CHANNEL_SESSION).set(session);
                // Inform the client that session creation was a success
                channel.writeAndFlush(new CtrlNewSessionStatus(CtrlNewSessionStatus.Status.SUCCESS));
            }
        } catch (Throwable e) {
            // Something unexpected went wrong
            channel.writeAndFlush(new CtrlNewSessionStatus(CtrlNewSessionStatus.Status.FAILURE_OTHER));
        }
    }
}
