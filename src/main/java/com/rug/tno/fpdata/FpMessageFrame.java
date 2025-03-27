package com.rug.tno.fpdata;

public record FpMessageFrame(
        long sequenceNumber,
        long sessionId,
        long lastReceivedMsgNo,
        FpPayload payload
) {
}
