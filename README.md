# Open-source-server-implementation-of-the-HLA4-Federate-Protocol

This project provides a bridge between the HLA4FP protocol and the traditional rti libraries. The server
will listen for HLA4FP connections and forward any information to the rti.

## Running in dev
This project can be run in dev using `mvn compile exec:exec`.

The project needs a traditional rti library to use for connections.
When ran via maven, it's configured to add any jar inside of `lib/` to the classpath. Place your rti's jar in
here and you should be ready to start developing.