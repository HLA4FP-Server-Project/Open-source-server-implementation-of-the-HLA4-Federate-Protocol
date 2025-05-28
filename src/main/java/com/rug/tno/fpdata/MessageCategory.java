package com.rug.tno.fpdata;

public enum MessageCategory {
    /**
     * A message encapsulating a HLA service. These messages contain information relevant
     * to the actual simulation.
     */
    HlaService,
    /**
     * A control message for the session layer. These messages contain information relevant
     * to maintaining the federate protocol connection.
     */
    Control
}
