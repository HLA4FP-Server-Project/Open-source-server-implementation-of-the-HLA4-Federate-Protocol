package com.rug.tno.fpdata;

import io.netty.buffer.ByteBuf;
import org.jspecify.annotations.NonNull;

public record CtrlNewSessionStatus(Status status) implements FpPayload {
    public static CtrlNewSessionStatus fromByteBuf(ByteBuf buf) {
        long status = buf.readUnsignedInt();
        return new CtrlNewSessionStatus(switch ((int)status) {
            case 0 -> Status.SUCCESS;
            case 1 -> Status.FAILURE_UNSUPPORTED_PROTOCOL_VERSION;
            case 2 -> Status.FAILURE_OUT_OF_RESOURCES;
            case 99 -> Status.FAILURE_OTHER;
            default -> throw new FpPayloadParseException("Invalid status id" + status);
        });
    }

    @Override
    public void writeToByteBuf(ByteBuf buf) {
            buf.writeByte(switch (this.status()) {
                case SUCCESS -> 0;
                case FAILURE_UNSUPPORTED_PROTOCOL_VERSION -> 1;
                case FAILURE_OUT_OF_RESOURCES -> 2;
                case FAILURE_OTHER -> 99;
            });
            buf.writeByte(0);
            buf.writeByte(0);
            buf.writeByte(0);
    }

    @Override
    public @NonNull MessageDirection direction() {
        return MessageDirection.FederateBound;
    }

    public enum Status {
        SUCCESS,
        FAILURE_UNSUPPORTED_PROTOCOL_VERSION,
        FAILURE_OUT_OF_RESOURCES,
        FAILURE_OTHER,
    }
}
