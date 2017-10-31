package org.costajlmpp.monitor.boundary;

import org.costajlmpp.reminders.boundary.ChangeEvent;
import org.costajlmpp.reminders.entity.ToDo;
import org.costajlmpp.trace.boundary.Tracer;

import javax.ejb.Singleton;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseBroadcaster;
import javax.ws.rs.sse.SseEventSink;


@Singleton
@Path("monitor")
public class MonitorResource {

    @Inject
    Tracer tracer;

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

    public void onToDoCreation(
            @ObservesAsync
            @ChangeEvent(ChangeEvent.Type.CREATION)
                    ToDo todo) {
        sendMessage(todo, "creation");
    }

    public void onToDoUpdates(
            @ObservesAsync
            @ChangeEvent(ChangeEvent.Type.UPDATE)
                    ToDo todo) {
        sendMessage(todo, "updates");
    }

    private void sendMessage(ToDo todo, String cause) {

        tracer.trace(".... " + Thread.currentThread().getName() + " - " + System.currentTimeMillis());

        if(this.sseBroadcaster != null && this.sse != null) {

            JsonObject payload = Json.createObjectBuilder().
                    add("id", todo.getId()).
                    add("done", todo.isDone()).
                    add("cause", cause).
                    add("caption", todo.getCaption()).
                    build();

            this.sseBroadcaster.broadcast(this.sse.newEvent("Notification-Channel", payload.toString()));
        }
    }


}
