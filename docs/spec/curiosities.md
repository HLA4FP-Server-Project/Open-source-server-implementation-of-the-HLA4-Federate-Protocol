# Curiosities
## Reconnecting
The spec does not actually explicitly state what should happen to the old connection if a new connection tries to
reconnect. For example, consider the scenario where the federate has made a socket connection A. It creates a new session
through connection A and obtains a session id. A while later, connection A breaks down. The federate realizes this, but
the RTI still has connection A marked as valid (it may take a while to realize no heartbeat messages are coming
in). Meanwhile, the federate creates a new socket connection B. The federate uses connection B to send a reconnect packet.
What should the RTI do with connection A at this point? As far as the RTI is concerned, connection A is still valid.

The only sensical option appears to be for the RTI to drop connection A. This is because the RTI cannot know if
connection A is dead or not. If the RTI would fail to reconnect if connection A is still valid, it might lead the
reconnection falsely failing. At worst this could prevent the reconnection altogether, at best this could delay the
reconnection until connection A times out. If the RTI would accept connection B without disconnecting connection A,
this leaves connection A in a weird phantom state, where it is undefined what should be done to any data sent via
connection A at this point.

The spec does mention a `RTI_TIMEOUT_HEART` and `FED_TIMEOUT_HEART` to define when the RTI and federate respectively
will consider a session to be inactive. It defines how long each party should wait for a new heartbeat. This is not
relevant to the problem however, since the spec does not forbid a federate from reconnecting earlier. In fact,
it mentions that if the socket connection is broken, that counts as a recoverable error regardless of if the timeout
expired. From testing, the Pitch [FedProClient](<https://github.com/Pitch-Technologies/FedProClient>) will correctly
start reconnecting as soon as a connection dies.

To conclude, there's a clear sensical solution to what should be done here: the old connection should be terminated.
But it is strange that the spec does not appear to explicitly mention anything on the topic.

## Session Ids
Each and every single packet sent between the rti and the federate contains the session id. I am not aware of any
practical purpose for doing so. The spec already forbids a single socket connection from hosting multiple sessions.
Thus, this number will always be the same for every single packet. It can potentially be used to detect if a connection
has been hijacked, but TCP already [has provisions](<https://medium.com/@SpoofIMEI/tcp-connection-hijacking-deep-dive-9bbe03fce9a9>)
to prevent this from happening.