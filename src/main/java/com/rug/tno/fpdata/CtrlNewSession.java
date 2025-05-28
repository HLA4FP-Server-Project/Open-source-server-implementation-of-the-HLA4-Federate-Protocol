package com.rug.tno.fpdata;

import io.netty.buffer.ByteBuf;
import org.jspecify.annotations.NonNull;

public record CtrlNewSession(long versionId) implements FpPayload {
    public static CtrlNewSession fromByteBuf(ByteBuf buf) {
        return new CtrlNewSession(buf.readUnsignedInt());
    }

    @Override
    public void writeToByteBuf(ByteBuf buf) {
        buf.writeInt((int)this.versionId);
    }

    @Override
    public boolean createsSession() {
        return true;
    }

    @Override
    public @NonNull MessageDirection direction() {
        return MessageDirection.RtiBound;
    }

    @Override
    public @NonNull MessageCategory category() {
        return MessageCategory.Control;
    }
}
