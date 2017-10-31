package org.costajlmpp.reminders.boundary;

import org.costajlmpp.JAXRSClientProvider;
import org.hamcrest.core.Is;
import org.junit.Rule;
import org.junit.Test;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

import static org.costajlmpp.JAXRSClientProvider.buildWithURI;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class ToDosResourceIT {

    // http://localhost:8080/jax-rs-server/rs/todos

    @Rule
    public JAXRSClientProvider provider = buildWithURI("http://localhost:8080/jax-rs-server/rs/todos");

    @Test
    public void todosSystemTest() {
        URI uri = this.createTodo();

        this.getAll();
    }

    public URI createTodo() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        JsonObject todoToCreate = builder.
                add("caption", "Implement ABCDE").
                add("priority", 42).
                add("description", "doing all until tomorrow").
                build();

        Response postResponse = this.provider.target().request().post(Entity.json(todoToCreate));
        assertThat(postResponse.getStatus(), Is.is(Response.Status.CREATED.getStatusCode()));
        URI location = postResponse.getLocation();
        System.out.println(location);
        assertNotNull(location);

        return location;
    }

    private void delete() {
        Response response = this.provider.target().
                path("23").
                request(MediaType.APPLICATION_JSON).
                delete();

        assertThat(response.getStatus(), is(204));
    }

    private void getAll() {
        Response response = this.provider.target().
                request(MediaType.APPLICATION_JSON).
                get();

        assertThat(response.getStatus(), is(200));
        final JsonArray payload = response.readEntity(JsonArray.class);
        System.out.println(payload);
        assertFalse(payload.isEmpty());
    }

}
