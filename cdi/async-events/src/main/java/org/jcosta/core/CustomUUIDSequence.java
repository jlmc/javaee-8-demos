package org.jcosta.core;

import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.sequencing.UUIDSequence;
import org.eclipse.persistence.sessions.Session;

public class CustomUUIDSequence implements SessionCustomizer {

    @Override
    public void customize(Session session) throws Exception {
        UUIDSequence sequence = new UUIDSequence("uuid");

        session.getLogin().addSequence(sequence);
    }
}
