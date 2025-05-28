package com.rug.tno.fpdata;

import io.netty.buffer.ByteBuf;
import org.jspecify.annotations.NonNull;

public record CtrlHeartbeatResponse(long responseTo) implements FpPayload {
    public static CtrlHeartbeatResponse fromByteBuf(ByteBuf buf) {
        return new CtrlHeartbeatResponse(
                buf.readUnsignedInt()
        );
    }

    @Override
    public void writeToByteBuf(ByteBuf buf) {
        buf.writeInt((int)this.responseTo);
    }

    @Override
    public @NonNull MessageDirection direction() {
        return MessageDirection.FederateBound;
    }
}
