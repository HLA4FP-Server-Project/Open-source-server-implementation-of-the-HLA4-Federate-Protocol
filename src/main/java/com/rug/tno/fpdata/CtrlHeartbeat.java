package com.rug.tno.fpdata;

import io.netty.buffer.ByteBuf;

public record CtrlHeartbeat() implements FpPayload {
    public static CtrlHeartbeat fromByteBuf(ByteBuf buf) {
        return new CtrlHeartbeat();
    }

    @Override
    public void writeToByteBuf(ByteBuf buf) {

    }
}
