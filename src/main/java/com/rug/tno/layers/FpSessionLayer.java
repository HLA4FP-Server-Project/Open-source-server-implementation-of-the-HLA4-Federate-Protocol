package com.rug.tno.layers;

import com.rug.tno.fpdata.CtrlNewSession;
import com.rug.tno.fpdata.CtrlNewSessionStatus;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class FpSessionLayer extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // TODO handle reconnections
        if (msg instanceof CtrlNewSession) {
            ctx.channel().writeAndFlush(new CtrlNewSessionStatus(CtrlNewSessionStatus.Status.SUCCESS));
        } else {
            // Pass everything else to the next layer
            ctx.fireChannelRead(msg);
        }
    }
}
