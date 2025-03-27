package com.rug.tno.fpdata;

public record FpMessage(
        long sequenceNumber,
        long sessionId,
        long lastReceivedMsgNo,
        FpPayload payload
) {
}
