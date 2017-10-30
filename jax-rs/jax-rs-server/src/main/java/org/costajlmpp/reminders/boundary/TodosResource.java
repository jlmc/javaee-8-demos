package org.costajlmpp.reminders.boundary;

import javax.ejb.Stateless;
import javax.json.JsonPatch;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.Path;
import java.util.Arrays;
import java.util.List;

@Stateless
@Path("todos")
public class TodosResource {

    @GET
    public List<String> search() {
        return Arrays.asList("a", "b");
    }

    @PATCH
    public void updateTodo(JsonPatch jsonPatch) {

        //TODO:: missing implementation

    }



}
