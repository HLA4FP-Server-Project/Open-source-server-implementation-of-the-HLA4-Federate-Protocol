package com.rug.tno.session;

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
    /**
     * A unique id representing this session
     * 8 bytes
     */
    private final long id;
    /**
     * The id of the message which was last sent by the federate.
     */
    private long lastReceivedMessageId;
    private long sequenceNumber;

    public SessionInfo(long id) {
        this.id = id;
        this.lastReceivedMessageId = -1;
        this.sequenceNumber = 0;
    }

    public long id() {
        return id;
    }

    public long lastReceivedMessageId() {
        return lastReceivedMessageId;
    }

    public void setLastReceivedMessageId(long lastReceivedMessageId) {
        this.lastReceivedMessageId = lastReceivedMessageId;
    }

    public long getNextSequenceNumber() {
        var sqn = sequenceNumber;
        this.sequenceNumber++;
        if (this.sequenceNumber > 0x7fffffffL) {
            this.sequenceNumber = 0;
        }
        return sqn;
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
