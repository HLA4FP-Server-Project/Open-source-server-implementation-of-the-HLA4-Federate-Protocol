package com.rug.tno.layers;

import com.rug.tno.pojo.CtrlNewSession;
import com.rug.tno.pojo.CtrlNewSessionStatus;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class DebugResponseLayer extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof CtrlNewSession) {
            ctx.channel().writeAndFlush(new CtrlNewSessionStatus(CtrlNewSessionStatus.Status.SUCCESS));
        }
    }
}
