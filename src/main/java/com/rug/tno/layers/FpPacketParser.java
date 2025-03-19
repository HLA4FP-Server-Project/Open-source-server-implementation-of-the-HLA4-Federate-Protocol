package com.rug.tno.layers;

import com.rug.tno.pojo.FpHeader;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.apache.commons.lang3.tuple.ImmutablePair;

/**
 * Handles delimiting a stream into distinct packets. Each packet has a header as specified by section
 * 12.13.4.5 of the FP specification
 */
public class FpPacketParser extends LengthFieldBasedFrameDecoder {
    public FpPacketParser() {
        super(Integer.MAX_VALUE, 0, 4, -4, 0);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        var bytebuf = (ByteBuf)super.decode(ctx, in);

        var packetSize = bytebuf.readUnsignedInt();
        var sequenceNumber = bytebuf.readUnsignedInt();;
        var sessionId = bytebuf.readLong();
        var lastReceivedMsgNo = bytebuf.readUnsignedInt();
        var messageType = bytebuf.readUnsignedInt();

        var header = new FpHeader(
                packetSize,
                sequenceNumber,
                sessionId,
                lastReceivedMsgNo,
                messageType
        );

        return new ImmutablePair<>(header, bytebuf);
    }
}
