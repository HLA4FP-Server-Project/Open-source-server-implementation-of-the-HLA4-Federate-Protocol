package com.rug.tno.layers;

import com.rug.tno.fpdata.CtrlNewSession;
import com.rug.tno.fpdata.FpMessageFrame;
import com.rug.tno.fpdata.MessageDirection;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

import static com.rug.tno.layers.FpSessionLayer.CHANNEL_SESSION;

/**
 * This class checks incoming/outgoing messages and detects errors. It mainly concerns itself with upholding
 * the rules from 12.13.4.6
 */
public class FpValidationLayer extends MessageToMessageCodec<FpMessageFrame,FpMessageFrame> {
    @Override
    protected void encode(ChannelHandlerContext ctx, FpMessageFrame message, List<Object> list) throws Exception {
        // We should only be sending messages destined for the federate (12.13.4.6 a2, b1)
        if (message.payload().direction() != MessageDirection.FederateBound) {
            // TODO Oh dear! We did something very wrong
            return;
        }

        // Forward it to the next layer
        list.add(message);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, FpMessageFrame message, List<Object> list) throws Exception {
        var session = ctx.channel().attr(CHANNEL_SESSION).get();

        if (session == null) {
            // The federate does not have a session yet, the only thing
            // it is allowed to do in this case is to send a message to create a connection (12.13.4.6 a1)
            if (!message.payload().createsSession()) {
                onInvalidMessage(ctx.channel());
                return;
            }
        } else {
            // This is not strictly in the spec, but it doesn't make sense to
            // create a session if you already have one
            if (message.payload().createsSession()) {
                onInvalidMessage(ctx.channel());
                return;
            }
        }

        // The federate should only be sending messages destined for us (12.13.4.6 a3, b2)
        if (message.payload().direction() != MessageDirection.RtiBound) {
            onInvalidMessage(ctx.channel());
            return;
        }

        // (12.13.4.6 a8)
        if (!(message.payload() instanceof CtrlNewSession)) {
            if (isSequenceNumberInvalid(message.sequenceNumber())) {
                onInvalidMessage(ctx.channel());
                return;
            }
        } else {
            if (message.sessionId() != 0) {
                // 12.13.4.5.1, the session should be zero for CTRL_NEW_SESSION
                onInvalidMessage(ctx.channel());
                return;
            }
        }

        // SUCCESS: The message passed validation. We can forward it to the next layer
        list.add(message);
    }

    private void onInvalidMessage(Channel channel) {
        // TODO properly log a message
        Thread.dumpStack();
        channel.close();
    }

    private boolean isSequenceNumberInvalid(long sqn) {
        // 12.13.4.5
        return sqn > 0x7fffffff;
    }
}
