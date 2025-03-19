package com.rug.tno.layers;

import com.rug.tno.pojo.CtrlNewSession;
import com.rug.tno.pojo.CtrlNewSessionStatus;
import com.rug.tno.pojo.FpHeader;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class MessageParser extends MessageToMessageCodec<Pair<FpHeader,ByteBuf>,Object> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object object, List<Object> list) throws Exception {
        var buf = channelHandlerContext.alloc().buffer();
        if (object instanceof CtrlNewSessionStatus c) {
            buf.writeByte(switch (c.status()) {
                case SUCCESS -> 0;
                case FAILURE_UNSUPPORTED_PROTOCOL_VERSION -> 1;
                case FAILURE_OUT_OF_RESOURCES -> 2;
                case FAILURE_OTHER -> 99;
            });
            buf.writeByte(0);
            buf.writeByte(0);
            buf.writeByte(0);
        }

        list.add(new ImmutablePair<>(
                new FpHeader(
                        24 + buf.writerIndex(),
                        0,
                        0,
                        -1,
                        2
                ),
                buf
        ));
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, Pair<FpHeader,ByteBuf> input, List<Object> list) throws Exception {
        var header = input.getLeft();
        list.add(switch ((int)header.messageType()) {
            case 1 -> new CtrlNewSession(input.getRight().readUnsignedInt());
            default -> throw new IllegalStateException("Unknown message "+input);
        });
    }
}
