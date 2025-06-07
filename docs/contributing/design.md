# Design
![](../diagrams/component_view.drawio)

The above is a global overview of how Open-source-server-implementation-of-the-HLA4-Federate-Protocol works. The project
makes use of [Netty](https://netty.io/) which can handle both TCP and Websockets. Netty is designed around the concept
of a pipeline, which Open-source-server-implementation-of-the-HLA4-Federate-Protocol makes heavy use of. The pipeline
consists of a number of handlers, each take in some input and return some output. The output then gets passed onto the
next part of the pipeline. When we send data, it travels in opposite order through the pipeline.

We have three main components inside the pipeline. First we've got handlers to deal with parsing the FP message, and
deserialize it into POJO. Then we have some layers to deal with Federate Protocol control messages. Finally, we take
the remaining HLA service calls and forward them to the RTI.

Here's the design in a bit more detail:

![](../diagrams/module_view.drawio)

`FpPacketDecoder`/`FpPacketEncoder` handle serializing/deserializing the packet header (12.13.4.5). `FpPayloadCodec` looks
at the type of payload, it will look this up in the `FpPayloadRegistry` to find out how to serialize/deserialize the type.
`FpValidationLayer` (not depicted here) contains some additional checks to ensure ingoing/outgoing packets conform to spec.
`FpSessionLayer` deals with Control messages. When the federate requests a new session, it attaches that data to the
socket connection (or, in Netty terms, the "Channel"). It defers to the `SessionManager` for actually creating Sessions.
The last layer is the `HlaForwardingLayer` which deals with sending the remaining HLA calls to the RTI.
Additionally, there's also `DebugPrintLayer` (not depicted here) for developer convenience, it logs anything which passes
through.

# The payload registry
The Federate Protocol defines a number of different message types (12.13.4.5.1 through 12.13.4.5.12). The
`FpPayloadRegistry` keeps track of these. It contains a registry of the packets: their numeric id, the POJO representing
it, as well as a function to deserialize some bytes. To add support for a new packet (eg: CTRL_SESSION_TERMINATED),
you must first create a new POJO to contain the data inside the packet (only the payload, the header is already parsed).
Then write a function to deserialize a set of bytes into this POJO. Implement `FpPayload`, according to the javadoc.
Finally, you can add your packet into `FpPayloadRegistry`, make sure to use the id that's specified by the spec (Table 34).

# One-time urls
When receiving FOM Modules via Federate Protocol, the FOM may be supplied as a byte array embedded inside
the protobuf request. Unfortunately, there doesn't appear to be a direct translation to this in the
Java RTI libraries, which only accept uri's. We explored the possibility of registering a custom
uri scheme through the jvm. However, there are issues with trying to do this whilst not breaking other scheme's.
The solution is the `OneTimeUseUrl` class. This will spin up a http server to serve the FOM. The class handles
creating a unique URL for anything you give it. Once the URL has been accessed once, the resource will be released
and that URL will no longer be accessable. The server will shut down once there are no more URLs left to serve.

This approach lets us avoid writing/managing files on disk. It should be relatively stable, the request is made from
inside our process and handled by our process. As such, there's low risk of firewalls interfering. Additionally,
the server is not bound to any specific port, and will choice any available one given by the OS, so there's no risk
of ports conflicting. Loss of efficiency should also be minimal, making a http call to yourself is not *that* uncommon
these days.