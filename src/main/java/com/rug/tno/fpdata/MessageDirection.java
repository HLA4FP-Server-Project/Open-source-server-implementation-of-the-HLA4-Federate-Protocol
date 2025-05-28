package com.rug.tno.fpdata;

/**
 * Represents the direction a message should travel in, as specified by 12.13.4.6
 */
public enum MessageDirection {
    /**
     * This message should be sent from the federate to the RTI
     */
    RtiBound,
    /**
     * This message should be sent from the RTI to the federate
     */
    FederateBound
}
