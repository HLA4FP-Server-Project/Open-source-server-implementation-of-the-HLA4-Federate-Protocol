package com.rug.tno.fpdata;

import io.netty.buffer.ByteBuf;
import org.jspecify.annotations.NonNull;

public interface FpPayload {
    void writeToByteBuf(ByteBuf buf);

    /**
     * @return true if this message is intended to set up a session.
     * Specifically for the purposes of 12.13.4.6: "No messages can be exchanged until a valid session has
     * been established, with the exception of Control messages for setting up such a session".
     */
    default boolean createsSession() {
        return false;
    }

    @NonNull
    MessageDirection direction();
}
