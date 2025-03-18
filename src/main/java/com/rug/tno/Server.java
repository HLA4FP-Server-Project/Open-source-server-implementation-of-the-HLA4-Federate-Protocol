package com.rug.tno;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Server {

    private Integer port;

    public Server(Integer port){
        this.port = port;
    }

    public void run() throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
            .channel(NioServerSocketChannel.class)
            .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    System.out.println("Incoming connection from " + ch.remoteAddress());
                    ch.pipeline().addLast(new ServerHandler());
                }
            })
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
