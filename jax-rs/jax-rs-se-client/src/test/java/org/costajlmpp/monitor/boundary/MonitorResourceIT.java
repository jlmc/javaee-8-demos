package org.costajlmpp.monitor.boundary;

import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.sse.InboundSseEvent;
import javax.ws.rs.sse.SseEventSource;
import java.util.concurrent.CountDownLatch;

public class MonitorResourceIT {

    //@Rule
    //public JAXRSClientProvider provider = buildWithURI("http://localhost:8080/jax-rs-server/rs/monitor");

    @Test
    public void monitor() throws InterruptedException {
        // This is just to don't finish the connection before receves 5 messages
        final CountDownLatch latch = new CountDownLatch(5);

        final Client client = ClientBuilder.newClient();
        final WebTarget target = client.target("http://localhost:8080/jax-rs-server/rs/monitor");

        try(SseEventSource eventSource = SseEventSource.target(target).build()) {

            eventSource.register(inboundSseEvent -> {
                processNotification(inboundSseEvent);
                latch.countDown();
            });

            eventSource.open();

            // wait for the 5 notifications
            latch.await();
        }

    }

    private void processNotification(InboundSseEvent inboundSseEvent) {
        System.out.println("---------------------");
        final String name = inboundSseEvent.getName();
        final String id = inboundSseEvent.getId();
        final String comment = inboundSseEvent.getComment();
        final long reconnectDelay = inboundSseEvent.getReconnectDelay();

        // final JsonObject payload = inboundSseEvent.readData(JsonObject.class);
        final String payload = inboundSseEvent.readData(String.class);


        System.out.println("channel name: " + name);
        System.out.println(payload);

        System.out.println("---------------------");
    }

}
