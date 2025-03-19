package com.rug.tno.connection;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * TcpConnection creates a ChannelHandler that can then handle the client
 */
public class TcpConnection implements HandlerFactory {

    private final HandlerFactory handlerFactory;

    /**
     * Basic constructor. Takes a single handler factory that constructs client handlers
     * You can also give it Handler::new as reference if Handler extends ChannelHandlerAdapter
     * @param handlerFactory client handler factory
     */
    public TcpConnection(HandlerFactory handlerFactory) {
        this.handlerFactory = handlerFactory;
    }

    @Override
    public ChannelHandler createHandler() {
        return new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) {
                System.out.println("Incoming connection from " + socketChannel.remoteAddress());
                socketChannel.pipeline().addLast(handlerFactory.createHandler());
            }
        };
    }
}
