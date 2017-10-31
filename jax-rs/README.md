# JAX-RS

Os leader da esficicação tinham como objetivo conseguir prover duas "grandes" novidades nesta realease:

* Reactive Client API
* Server-sent events ()

* Integração com outras novas especificações
    - JSON-B entity providers
    - Suporte pata HTTP PATCH request


----

## Reactive Client API
é um melhoramento a nivel do client.




---
## Server-sent events
 * Server sent Events foram especificados pela nova espeficificação do HTTP.
 
 * Permite ao cliente subecrever um sequencia de notificaçoes de eventos do servidor.
 * Funciona partindo do principio de que:
    1. o client estabelece uma Http conncetion: 
        - Long-Running connection que pode ser reutilizada por multiplos send Events do servidor, 
        - A connection será fechado apenas pelo cliente
    2. Tem um Media Type especial: SERVER_SENT_EVENTS = "text/event-stream";
    3. O evento pssui um conjunto de informação util:
       - event name (identificaçao do canal), data, id, retry e comments fields
       - o campo data possui o payload que normalmente mais interessa


---

 #### Server Side
 
  * SseEventSink - 
        resource injectado como parametro do metodo, 
        corresponde a uma instancia unica de uma http connection 
        será usada para submeter os enventos para os clientes
  
  * Sse: Pode ser vista como uma fabrica paa criar eventos broadcask que seram submetidos
  
  * OutBoundSseEvent - Representation of a single outbound Server-sent event, Used on the server side, when creating and sending an event to a client or when broadcasting. 
 
  * SseBroadcaster - Used on the server side, when creating and sending an event to a client or when broadcasting.
  
```java
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

```


 #### Client Side
 
 ```java
 import org.junit.Test;
 
 import javax.json.JsonObject;
 import javax.ws.rs.client.Client;
 import javax.ws.rs.client.ClientBuilder;
 import javax.ws.rs.client.WebTarget;
 import javax.ws.rs.sse.InboundSseEvent;
 import javax.ws.rs.sse.SseEventSource;
 

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
 ```
 
 git


 NOTES:
 
 https://www.slideshare.net/delabassee/jaxrs-21-reloaded
 http://ridingthecrest.com/blog/2017/02/22/jax-rs-2_1-new-feature-introduction.html
 
 
 Serve Sent Event - https://github.com/jersey/jersey/tree/master/examples/server-sent-events-jersey