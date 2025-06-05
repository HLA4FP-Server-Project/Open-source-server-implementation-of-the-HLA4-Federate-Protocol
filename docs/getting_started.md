# Getting started

## What is Open-source-server-implementation-of-the-HLA4-Federate-Protocol for?
As part of the HLA4 spec, a new Federate Protocol was added. This is a standardized protocol for communicating with the
RTI. Having such a standardized protocol has a number of advantages, for example the protocol can be implemented in any
protocol which supports TCP or Websockets.

The Open-source-server-implementation-of-the-HLA4-Federate-Protocol project attempts to help adoption of the Federate
Protocol. It makes use of the "regular" way of connecting to an RTI (via an SDK), and exposes a Federate Protocol
server. It can be used to give Federate Protocol support to an RTI that does not normally support it.

Without Open-source-server-implementation-of-the-HLA4-Federate-Protocol:
```mermaid
graph TD
Federate <-->|proprietary protocol| RTI
```

With Open-source-server-implementation-of-the-HLA4-Federate-Protocol:
```mermaid
graph TD
Federate <-->|Federate Protocol| Open-source-server-implementation-of-the-HLA4-Federate-Protocol
Open-source-server-implementation-of-the-HLA4-Federate-Protocol <-->|proprietary protocol| RTI
```

When using multiple federates, you can choose if you want each one to have their own instance, or share one
across the board
```mermaid
graph TD
FA1[Federate];
FA2[Federate];
FA3[Federate];

FB1[Federate];
FB2[Federate];
FB3[Federate];

OA1[Open-source-server-implementation-of-the-HLA4-Federate-Protocol];
OA2[Open-source-server-implementation-of-the-HLA4-Federate-Protocol];
OA3[Open-source-server-implementation-of-the-HLA4-Federate-Protocol];
OB[Open-source-server-implementation-of-the-HLA4-Federate-Protocol];

RTIA[RTI];
RTIB[RTI];

FA1 <--> OA1;
FA2 <--> OA2;
FA3 <--> OA3;
FB1 <--> OB;
FB2 <--> OB;
FB3 <--> OB;

OA1 <--> RTIA;
OA2 <--> RTIA;
OA3 <--> RTIA;
OB <--> RTIB;
```

## Running the server (with  the jar file)
The server can be run using the provided jar file, which can be
downloaded from [Github releases](https://github.com/HLA4FP-Server-Project/Open-source-server-implementation-of-the-HLA4-Federate-Protocol/releases).
When running the jar, please include your RTI's SDK in the classpath.

## Valid RTI's
The Open-source-server-implementation-of-the-HLA4-Federate-Protocol supports any RTI which supports HLA4. It relies
on the RTI's Java SDK. Please refer to your RTI's documentation, it should mention a jar file which you are to
include in any Java-based federates. This is the jar file which Open-source-server-implementation-of-the-HLA4-Federate-Protocol
requires.