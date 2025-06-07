# On support for HLA/HLA Evolved
The Open-source-server-implementation-of-the-HLA4-Federate-Protocol does not support HLA or HLA Evolved. This is
because the Federate Protocol was designed for HLA4. The IEEE 1516 specification only defines how the Federate
Protocol works for HLA4, any solution which support HLA/HLA Evolved must operate outside the spec.

In theory, it is perfectly possible to run HLA/HLA Evolved over the Federate Protocol, but there are two main hurdles
to overcome. Firstly, you would need to agree on a version to signify HLA/HLA Evolved. When a Federate Protocol session
is created, the federate defines which version of the protocol is used via a 4-byte integer. Currently, the only valid
version defined in the spec is `1`, signifying HLA4. Unless additional specifications are made, there's no way to
signify that you want to create an HLA/HLA Evolved session.

Secondly, you would need to agree on a set of standardized protobuf definitions for HLA/HLA Evolved. These definitions
should somehow be standardized to prevent conflicts. Different implementations agreeing on different protobuf
definitions could turn into a big mess.