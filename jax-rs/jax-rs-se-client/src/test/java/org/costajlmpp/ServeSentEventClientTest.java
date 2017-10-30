package org.costajlmpp;

import org.junit.Test;

import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.sse.InboundSseEvent;
import javax.ws.rs.sse.SseEventSource;

/**
 * Client Side
 */
public class ServeSentEventClientTest {

    @Test
    public void notifications() {

        final Client client = ClientBuilder.newClient();
        final WebTarget target = client.target("http://localhost:8080/sse/rs/beats");

        try (SseEventSource eventSource = SseEventSource.target(target).build()){

            eventSource.register(this::processNotification);
            eventSource.open();

        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

    }

    private void processNotification(InboundSseEvent inboundSseEvent) {
        System.out.println("---------------------");
        final String name = inboundSseEvent.getName();
        final String id = inboundSseEvent.getId();
        final String comment = inboundSseEvent.getComment();
        final long reconnectDelay = inboundSseEvent.getReconnectDelay();

        final JsonObject payload = inboundSseEvent.readData(JsonObject.class);

        System.out.println("channel name: " + name);
        System.out.println(payload);

        System.out.println("---------------------");

    }

}
