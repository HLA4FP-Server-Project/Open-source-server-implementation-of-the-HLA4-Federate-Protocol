package com.rug.tno.fpdata;

public class FpPayloadParseException extends RuntimeException {
    public FpPayloadParseException(String message) {
        super(message);
    }

    public FpPayloadParseException(Throwable cause) {
        super(cause);
    }
}
