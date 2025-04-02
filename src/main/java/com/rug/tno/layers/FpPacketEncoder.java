package com.rug.tno.layers;

import com.rug.tno.fpdata.FpHeader;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.apache.commons.lang3.tuple.Pair;

public class FpPacketEncoder extends MessageToByteEncoder<Pair<FpHeader,ByteBuf>> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Pair<FpHeader,ByteBuf> input, ByteBuf byteBuf) throws Exception {
        var header = input.getLeft();
        byteBuf.writeInt((int)header.packetSize());
        byteBuf.writeInt((int)header.sequenceNumber());
        byteBuf.writeLong(header.sessionId());
        byteBuf.writeInt((int)header.lastReceivedMsgNo());
        byteBuf.writeInt((int)header.messageType());
        input.getRight().forEachByte(b -> {
            byteBuf.writeByte(b);
            return true;
        });
    }
}
