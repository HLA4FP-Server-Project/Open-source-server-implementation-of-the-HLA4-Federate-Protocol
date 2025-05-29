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
     * A unique id representing this session.
     * All 8 bytes of this number are used.
     */
    private final long id;
    /**
     * The sequence number of the last message we received from the federate.
     * Only 4 bytes of this number are used.
     */
    private long lastReceivedMessageId;
    /**
     * The current sequence number used for this session. Each message sent gets a sequence number, and
     * this increases monotonically.
     * Only 4 bytes of this number are used.
     */
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

    /**
     * Marks a federate message as being received
     */
    public void markMessageReceived(long lastReceivedMessageId) {
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
