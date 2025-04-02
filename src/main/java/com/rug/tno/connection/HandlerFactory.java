package com.rug.tno.connection;

import io.netty.channel.ChannelHandler;

/**
 * Factory to produce channel handlers
 * Each class in the connection module needs to implement this,
 * so it can then be passed down to the server, so it can
 * create the initial connection according to the desired
 * parameters
 */
public interface HandlerFactory {

    /**
     * create a single handler to be passed to the server
     * @return handler which handles the connection once
     *      instantiated
     */
    ChannelHandler createHandler();

}
