package org.jcosta.tracing.boundary;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.util.logging.Logger;

public class LoggerExposer {

    @Produces
    public Logger expose(final InjectionPoint ip) {
        final String loggerName = ip.getMember().getDeclaringClass().getName();
        return Logger.getLogger(loggerName);
    }
}
