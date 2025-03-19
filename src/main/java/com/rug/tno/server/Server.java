package com.rug.tno.server;

import com.rug.tno.connection.HandlerFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class Server {

    private final Integer port;
    private final HandlerFactory channelInitializer;

    public Server(Integer port, HandlerFactory channelInitializer){
        this.port = port;
        this.channelInitializer = channelInitializer;
    }

    public void run() throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap b = new ServerBootstrap()
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(
                        channelInitializer.createHandler()
                )
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture f = b.bind(new InetSocketAddress("0.0.0.0", port)).sync();

            InetSocketAddress socket = (InetSocketAddress) f.channel().localAddress();

            System.out.println("Server open at address: " + socket.getAddress() + " on port: " + socket.getPort());

            f.channel().closeFuture().sync();

        }finally{
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
