package com.rug.tno.session;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;

public class SessionManager {
    private final SecureRandom random;
    private final Set<SessionInfo> sessions;

    public SessionManager() {
        this.random = new SecureRandom();
        this.sessions = new HashSet<>();
    }

    public SessionInfo createNewSession() {
        var id = random.nextLong();
        return new SessionInfo(id);
    }
}
