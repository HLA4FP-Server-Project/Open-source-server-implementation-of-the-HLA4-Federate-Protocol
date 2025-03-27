package com.rug.tno.layers;

import com.rug.tno.pojo.*;
import hla.rti1516_2024.fedpro.CallRequest;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class MessageParser extends MessageToMessageCodec<Pair<FpHeader,ByteBuf>,Object> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object object, List<Object> list) throws Exception {
        var buf = channelHandlerContext.alloc().buffer();
        int messageType = -1;
        if (object instanceof CtrlNewSessionStatus c) {
            messageType = 2;
            buf.writeByte(switch (c.status()) {
                case SUCCESS -> 0;
                case FAILURE_UNSUPPORTED_PROTOCOL_VERSION -> 1;
                case FAILURE_OUT_OF_RESOURCES -> 2;
                case FAILURE_OTHER -> 99;
            });
            buf.writeByte(0);
            buf.writeByte(0);
            buf.writeByte(0);
        } else if (object instanceof HlaCallResponse r) {
            messageType = 21;
            r.body().writeTo(new OutputStream() {
                @Override
                public void write(int b) throws IOException {
                    buf.writeByte(b);
                }
            });
        }

        list.add(new ImmutablePair<>(
                new FpHeader(
                        24 + buf.writerIndex(),
                        0,
                        0,
                        -1,
                        messageType
                ),
                buf
        ));
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, Pair<FpHeader,ByteBuf> input, List<Object> list) throws Exception {
        var header = input.getLeft();
        list.add(switch ((int)header.messageType()) {
            case 1 -> new CtrlNewSession(input.getRight().readUnsignedInt());
            case 20 -> new HlaCallRequest(CallRequest.parseFrom(input.getRight().nioBuffer()));
            default -> throw new IllegalStateException("Unknown message "+input);
        });
    }
}
