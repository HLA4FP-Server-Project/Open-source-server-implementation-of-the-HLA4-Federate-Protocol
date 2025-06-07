# Security considerations
The Federate Protocol carries a number of security considerations with it.

## Session ID
The Federate Protocol assigns a session id for each connection. This session id is absolutely vital to the protocol's
security. This is because the session id can be used to *reconnect* a session. This has big implications when combined
with other HLA security features, because such a reconnect works on a level *below* HLA. This means that the reconnect
happens fully transparently to any of the HLA api's, and as such it can be used to potentially bypass HLA security
features. If the session id is not properly secured, an adversary could wait until the federate presents its credentials,
and then use the reconnection feature of HLA to hijack the session.

On the RTI side, this means that the session id *must* be sourced from a trusted, cryptographically random, source.
Any predictability in the session id is a security vulnerability.[^1]

Additionally, it means that session ids can be considered sensitive data. They can be viewed as a temporary access token
that is used by the federate. Federates should avoid leaking their session id.

## Underlying protocol
The Federate Protocol can be run via TCP, TLS/TCP, Websocket, and Secure Websocket. Only TLS/TCP and Secure Websocket
should be considered secure. The other two transmit data in plain-text. Not only can this lead to all communication
between the RTI and the federate being leaked, it can also lead to the federate being hijacked entirely, since all
packets also contain the session id.

[^1]:
    The Open-source-server-implementation-of-the-HLA4-Federate-Protocol makes use of
    Java's [SecureRandom](<https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/security/SecureRandom.html>)
    for this purpose. Other programming languages should have their own version of a cryptographically strong random
    number generator. Make sure to use the full 8 bytes of entropy, but disallow `0` as that's an invalid session id.