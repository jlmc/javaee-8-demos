package org.costajlmpp.broadcast;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseBroadcaster;
import javax.ws.rs.sse.SseEventSink;
import java.time.LocalDateTime;

@Singleton
@Path("beats") // curl -i  http://localhost:8080/sse/rs/beats
public class BroadcastsResource {

    private Sse sse;
    private SseBroadcaster sseBroadcaster;

    @GET
    @Produces({MediaType.SERVER_SENT_EVENTS})
    public void register(@Context Sse sse, @Context SseEventSink eventSink) {

        this.sse = sse;

        if (this.sseBroadcaster == null) {
            this.sseBroadcaster = this.sse.newBroadcaster();
        }

        this.sseBroadcaster.register(eventSink);
    }


    @Schedule(second = "*/2", minute = "*", hour = "*")
    public void beat () {
        System.out.println(".... " + Thread.currentThread().getName() + " - " + System.currentTimeMillis());

        if(this.sseBroadcaster != null && this.sse != null) {
            this.sseBroadcaster.broadcast(this.sse.newEvent("time: " + LocalDateTime.now().toString()));
        }
    }

}
