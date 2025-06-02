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
        // Generate a random id
        long id;
        do {
            id = random.nextLong();
        } while (id == 0); // zero is an illegal id

        return new SessionInfo(id);
    }
}
