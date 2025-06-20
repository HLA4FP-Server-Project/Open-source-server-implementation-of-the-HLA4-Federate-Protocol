package com.rug.tno.fpdata;

import com.google.protobuf.InvalidProtocolBufferException;
import hla.rti1516_2024.fedpro.CallRequest;
import hla.rti1516_2024.fedpro.CallbackRequest;
import io.netty.buffer.ByteBuf;
import org.jspecify.annotations.NonNull;

import java.io.IOException;
import java.io.OutputStream;

public record HlaCallbackRequest(CallbackRequest body) implements FpPayload {
    public static HlaCallbackRequest fromByteBuf(ByteBuf buf) {
        try {
            return new HlaCallbackRequest(CallbackRequest.parseFrom(buf.nioBuffer()));
        } catch (InvalidProtocolBufferException e) {
            throw new FpPayloadParseException(e);
        }
    }

    @Override
    public void writeToByteBuf(ByteBuf buf) {
        try {
            this.body().writeTo(new OutputStream() {
                @Override
                public void write(int b) throws IOException {
                    buf.writeByte(b);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public @NonNull MessageDirection direction() {
        return MessageDirection.FederateBound;
    }

    @Override
    public @NonNull MessageCategory category() {
        return MessageCategory.HlaService;
    }
}
