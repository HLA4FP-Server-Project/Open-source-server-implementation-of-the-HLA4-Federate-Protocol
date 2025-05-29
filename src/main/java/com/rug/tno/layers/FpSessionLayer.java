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

    public FpSessionLayer(SessionManager sessionMngr) {
        this.sessionMngr = sessionMngr;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, FpPayload payload, List<Object> list) throws Exception {
        // In the context of a channel, we should either
        //  * Send all messages with a non-null session attribute
        //  * Send only a single messages (informing a session could not be created)
        // The IEEE spec currently enforces this. But if this rule is ever broken, it'll
        // cause multiple messages to be sent with the same sequence number. It's unclear what
        // sequence number we should be using whilst sending messages without a session. So if
        // we're ever doing that something went wrong

        // The spec currently states that no messages can be exchanged without a valid session,
        // except control messages setting up such a connection (12.13.4.6).
        // It also states the LRM field should ignore non-hla messages
        // The way we handle the Last Received Message field relies on this. The LRM field should be
        // invalid iff there's no last received message. Which, in addition to the previous two statements,
        // means that we can conclude the LRM field should always be invalid if there's no session.

        var session = ctx.channel().attr(CHANNEL_SESSION).get();
        var sessionId = session == null ? 0 : session.id();
        // -1 is an invalid sequence number. We're supposed to send that if there are no previous messages
        var lastReceivedMessageId = session == null ? -1 : session.lastReceivedMessageId();
        var sequenceNumber = session == null ? 0 : session.getNextSequenceNumber();

        list.add(new FpMessageFrame(
                sequenceNumber,
                sessionId,
                lastReceivedMessageId,
                payload
        ));
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, FpMessageFrame message, List<Object> list) throws Exception {
        var session = ctx.channel().attr(CHANNEL_SESSION).get();

        // Handle session messages
        if (session != null) {
            session.setLastReceivedMessageId(message.sessionId());
        }

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
                channel.writeAndFlush(new CtrlNewSessionStatus(NewSessionStatusCode.FAILURE_OTHER));
            } else {
                // Create a new session and associate it with this channel
                var session = sessionMngr.createNewSession();
                channel.attr(CHANNEL_SESSION).set(session);
                // Inform the client that session creation was a success
                channel.writeAndFlush(new CtrlNewSessionStatus(NewSessionStatusCode.SUCCESS));
            }
        } catch (Throwable e) {
            // Something unexpected went wrong
            channel.writeAndFlush(new CtrlNewSessionStatus(NewSessionStatusCode.FAILURE_OTHER));
        }
    }
}
