package org.costajlmpp;

import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Future;

public class MessagesResourceTest {

    //@Rule
    //public JAXRSClientProvider provider = buildWithURI("http://localhost:8080/doit/resources/todos");

    public void old() {
        /**
         * no jax-rs 2.0 esta era forma
         */
        final Client client = ClientBuilder.newClient();
        final WebTarget target = client.target("http://localhost:8080/jax-rs-server/rs/todos");


        final String result = target.request(MediaType.APPLICATION_JSON).get(String.class);


    }

    public void oldAsync() {
        /**
         * no jax-rs 2.0 esta era forma
         */
        final Client client = ClientBuilder.newClient();
        final WebTarget target = client.target("http://localhost:8080/jax-rs-server/rs/todos");


        final Future<String> result = target.
                request(MediaType.APPLICATION_JSON).
                async().
                get(String.class);


    }

    public void oldAsyncWithHandler() {

        /**
         * no jax-rs 2.0 esta era forma
         */
        final Client client = ClientBuilder.newClient();
        final WebTarget target = client.target("http://localhost:8080/jax-rs-server/rs/todos");


        final Future<ToDo> result = target.
                request(MediaType.APPLICATION_JSON).
                async().
                get(new InvocationCallback<ToDo>() {
                    @Override
                    public void completed(ToDo toDo) {
                        System.out.println("Execute some task on success " + toDo);
                    }

                    @Override
                    public void failed(Throwable throwable) {
                        System.out.println("Execute some task on Fail " + throwable);
                    }
                });


    }

    /**
     * Esta ultima duas ultimas abordagens foram vistas como possibilidades para melhor a especificação,
     * Pois com o Java 8 esxistem os CompletableFuture e então podemos contruir pipelines no client
     *
     * isto possibilita uma maior flexibilidade
     */

    public void newAsync() {
        /**
         * no jax-rs 2.1
         */
        final Client client = ClientBuilder.newClient();
        final WebTarget target = client.target("http://localhost:8080/jax-rs-server/rs/todos");


        final CompletionStage<String> onGetCompleted = target.
                request(MediaType.APPLICATION_JSON).
                rx().
                get(String.class);

        onGetCompleted.thenAccept(System.out::println);
    }

    public void newAsyncCombinerequest() {
        /**
         * no jax-rs 2.1
         */

        final Client client = ClientBuilder.newClient();
        final CompletionStage<JsonObject> onGetToDosCompleted = client.target("http://localhost:8080/jax-rs-server/rs/todos/1").
                request(MediaType.APPLICATION_JSON).
                rx().
                get(JsonObject.class);

        // Possible Pipeline
        onGetToDosCompleted.
                thenApply(this::approves).
                thenAccept(this::submitChanges);
    }

    private void submitChanges(JsonObject jsonObject) {
        final Client client = ClientBuilder.newClient();
        final CompletionStage<Response> onPostCOmplete = client.target("http://localhost:8080/jax-rs-server/rs/todos").
                request(MediaType.APPLICATION_JSON).
                rx().
                post(Entity.json(jsonObject));
    }

    private JsonObject approves(JsonObject jsonObject) {
        final JsonValue done = jsonObject.replace("done", JsonValue.TRUE);
        return jsonObject;
    }


}
