package com.rug.tno.fpdata;

import io.netty.buffer.ByteBuf;

public interface FpPayload {
    void writeToByteBuf(ByteBuf buf);
}
