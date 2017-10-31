package org.costajlmpp.trace.boundary;

@FunctionalInterface
public interface Tracer {
    void trace(String msg);
}
