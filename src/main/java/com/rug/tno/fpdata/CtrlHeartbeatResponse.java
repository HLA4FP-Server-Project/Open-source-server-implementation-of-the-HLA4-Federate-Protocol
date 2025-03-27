package com.rug.tno.fpdata;

import io.netty.buffer.ByteBuf;

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
}
