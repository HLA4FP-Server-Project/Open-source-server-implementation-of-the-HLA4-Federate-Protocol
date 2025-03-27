package com.rug.tno.layers;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

public class DebugPrintLayer extends MessageToMessageCodec<Object,Object> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object object, List<Object> list) throws Exception {
        System.out.println("Outbound: "+object);
        list.add(object);
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, Object object, List<Object> list) throws Exception {
        System.out.println("Inbound: "+object);
        list.add(object);
    }
}
