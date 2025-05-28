package com.rug.tno.session;

import io.netty.util.AttributeKey;

import java.util.Objects;

/**
 * Represents all information about a session. Note the distinction between a session and a
 * connection. "Session" is used here as per its definition in IEEE 1516, whilst a "connection" refers to a
 * tcp/websocket connection (or an {@link io.netty.channel.Channel} in Netty terms).
 * <p>
 * A session always belongs to one or zero connections at a time. When a connection is lost, the session
 * corresponding to that connection is retained. A new connection may then reconnect to an existing session.
 * </p>
 */
public class SessionInfo {
    private final long id;

    public SessionInfo(long id) {
        this.id = id;
    }

    public long id() {
        return id;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        SessionInfo that = (SessionInfo)object;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
