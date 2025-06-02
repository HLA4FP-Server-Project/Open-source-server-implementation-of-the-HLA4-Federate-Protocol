package com.rug.tno.fpdata;

import io.netty.buffer.ByteBuf;
import org.jspecify.annotations.NonNull;

public record CtrlNewSessionStatus(NewSessionStatusCode status) implements FpPayload {
    public static CtrlNewSessionStatus fromByteBuf(ByteBuf buf) {
        long status = buf.readUnsignedInt();
        return new CtrlNewSessionStatus(NewSessionStatusCode.fromBytes(status));
    }

    @Override
    public void writeToByteBuf(ByteBuf buf) {
            buf.writeInt((int)status.toBytes());
    }

    @Override
    public @NonNull MessageDirection direction() {
        return MessageDirection.FederateBound;
    }

    @Override
    public @NonNull MessageCategory category() {
        return MessageCategory.Control;
    }
}
