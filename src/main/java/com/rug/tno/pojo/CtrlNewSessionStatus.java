package com.rug.tno.pojo;

public record CtrlNewSessionStatus(Status status) {
    public enum Status {
        SUCCESS,
        FAILURE_UNSUPPORTED_PROTOCOL_VERSION,
        FAILURE_OUT_OF_RESOURCES,
        FAILURE_OTHER,
    }
}
