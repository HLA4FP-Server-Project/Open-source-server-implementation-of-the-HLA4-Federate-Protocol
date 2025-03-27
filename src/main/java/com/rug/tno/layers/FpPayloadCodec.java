package com.rug.tno.layers;

import com.rug.tno.fpdata.*;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class FpPayloadCodec extends MessageToMessageCodec<Pair<FpHeader,ByteBuf>,FpMessageFrame> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, FpMessageFrame message, List<Object> list) throws Exception {
        var buf = channelHandlerContext.alloc().buffer();
        message.payload().writeToByteBuf(buf);

        list.add(new ImmutablePair<>(
                new FpHeader(
                        24 + buf.writerIndex(),
                        message.sequenceNumber(),
                        message.sessionId(),
                        message.lastReceivedMsgNo(),
                        FpPayloadRegistry.getIdForPayload(message.payload())
                ),
                buf
        ));
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, Pair<FpHeader,ByteBuf> input, List<Object> list) throws Exception {
        var header = input.getLeft();

        // Look up which message type this is, and parse it as that message type
        var factory = FpPayloadRegistry.getFactoryForId(header.messageType());
        if (factory == null) {
            throw new IllegalStateException("Unknown message type "+header.messageType());
        }
        var payload = factory.apply(input.getRight());

        list.add(new FpMessageFrame(
                header.sequenceNumber(),
                header.sessionId(),
                header.lastReceivedMsgNo(),
                payload
        ));
    }
}
