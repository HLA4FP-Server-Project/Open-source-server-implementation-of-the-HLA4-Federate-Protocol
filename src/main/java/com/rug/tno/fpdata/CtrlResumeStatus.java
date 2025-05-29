package com.rug.tno.fpdata;

import io.netty.buffer.ByteBuf;
import org.jspecify.annotations.NonNull;

public record CtrlResumeStatus(NewSessionStatusCode status) implements FpPayload {
    public static CtrlResumeStatus fromByteBuf(ByteBuf buf) {
        long status = buf.readUnsignedInt();
        return new CtrlResumeStatus(NewSessionStatusCode.fromBytes(status));
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
