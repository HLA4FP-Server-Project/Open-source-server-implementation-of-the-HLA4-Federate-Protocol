package com.rug.tno.connection;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

import java.util.function.Consumer;

/**
 * TcpConnection creates a ChannelHandler that can then handle the client
 */
public class TcpConnection implements HandlerFactory {

    private final Consumer<ChannelPipeline> pipelineConfigurer;

    /**
     * Basic constructor. Takes a single handler factory that constructs client handlers
     * You can also give it Handler::new as reference if Handler extends ChannelHandlerAdapter
     * @param pipelineConfigurer configuration for the netty pipeline
     */
    public TcpConnection(Consumer<ChannelPipeline> pipelineConfigurer) {
        this.pipelineConfigurer = pipelineConfigurer;
    }

    @Override
    public ChannelHandler createHandler() {
        return new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) {
                System.out.println("Incoming connection from " + socketChannel.remoteAddress());
                pipelineConfigurer.accept(socketChannel.pipeline());
            }
        };
    }
}
