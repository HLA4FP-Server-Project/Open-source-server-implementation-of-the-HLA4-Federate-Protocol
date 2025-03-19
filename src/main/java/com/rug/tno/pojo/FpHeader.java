package com.rug.tno.pojo;

/**
 * The fp protocol header, which is sent before each protobuf message.
 * It is defined in section 12.13.4.5 "Protocol Header and Payload Specification"
 */
public record FpHeader(
        long packetSize,
        long sequenceNumber,
        long sessionId,
        long lastReceivedMsgNo,
        long messageType
) {
}
