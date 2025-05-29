package com.rug.tno.fpdata;

/**
 * An indication of the state of a newly created or newly reconnected session
 */
public enum NewSessionStatusCode {
    SUCCESS(0),
    FAILURE_UNSUPPORTED_PROTOCOL_VERSION(1),
    FAILURE_OUT_OF_RESOURCES(2),
    FAILURE_OTHER(99);

    private final long code;

    NewSessionStatusCode(long code) {
        if (code > 0xFFFFFFFFL) {
            throw new IllegalArgumentException("Status code is only 4 bytes");
        }
        this.code = (byte)code;
    }

    /**
     * Parses a status code, given its 4-byte wire representation
     */
    public static NewSessionStatusCode fromBytes(long v) {
        for (var value : NewSessionStatusCode.values()) {
            if (value.code == v) {
                return value;
            }
        }
        throw new FpPayloadParseException("Invalid status code" + v);
    }

    public long toBytes() {
        return this.code;
    }
}
