package com.rug.tno.fpdata;

import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class FpPayloadRegistry {
    private static final Map<Long, Function<ByteBuf, ? extends FpPayload>> PAYLOAD_FACTORIES = new HashMap<>();
    private static final Map<Class<?>, Long> PAYLOAD_TO_ID = new HashMap<>();

    static {
        registerPayload(1, CtrlNewSession.class, CtrlNewSession::fromByteBuf);
        registerPayload(2, CtrlNewSessionStatus.class, CtrlNewSessionStatus::fromByteBuf);
        registerPayload(20, HlaCallRequest.class, HlaCallRequest::fromByteBuf);
        registerPayload(21, HlaCallResponse.class, HlaCallResponse::fromByteBuf);
        registerPayload(22, HlaCallbackRequest.class, HlaCallbackRequest::fromByteBuf);
        registerPayload(23, HlaCallbackResponse.class, HlaCallbackResponse::fromByteBuf);
    }

    private static <T extends FpPayload> void registerPayload(long payloadId, Class<T> type, Function<ByteBuf, T> factory) {
        PAYLOAD_FACTORIES.put(payloadId, factory);
        PAYLOAD_TO_ID.put(type, payloadId);
    }

    public static Function<ByteBuf, ? extends FpPayload> getFactoryForId(long id) {
        return PAYLOAD_FACTORIES.get(id);
    }

    public static long getIdForPayload(FpPayload payload) {
        return PAYLOAD_TO_ID.get(payload.getClass());
    }
}
