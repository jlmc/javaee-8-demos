package org.costajlmpp.trace.boundary;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TracerProducer {

    @Produces
    public Tracer produceTrace(InjectionPoint ip) {
        Class<?> injectionTarget = ip.getMember().getDeclaringClass();
        final String className = injectionTarget.getName();
        final Logger logger = Logger.getLogger(className);

        return (msg) -> logger.log(Level.INFO, msg);
    }
}
