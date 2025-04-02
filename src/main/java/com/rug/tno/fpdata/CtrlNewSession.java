package com.rug.tno.fpdata;

import io.netty.buffer.ByteBuf;

public record CtrlNewSession(long versionId) implements FpPayload {
    public static CtrlNewSession fromByteBuf(ByteBuf buf) {
        return new CtrlNewSession(buf.readUnsignedInt());
    }

    @Override
    public void writeToByteBuf(ByteBuf buf) {
        buf.writeInt((int)this.versionId);
    }
}
