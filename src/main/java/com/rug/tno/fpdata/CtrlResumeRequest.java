package com.rug.tno.fpdata;

import io.netty.buffer.ByteBuf;
import org.jspecify.annotations.NonNull;

public record CtrlResumeRequest(long lrrSequenceNumber, long oafSequenceNumber) implements FpPayload {
    public static CtrlResumeRequest fromByteBuf(ByteBuf buf) {
        return new CtrlResumeRequest(buf.readUnsignedInt(), buf.readUnsignedInt());
    }

    @Override
    public void writeToByteBuf(ByteBuf buf) {
        buf.writeInt((int)this.lrrSequenceNumber);
        buf.writeInt((int)this.oafSequenceNumber);
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
