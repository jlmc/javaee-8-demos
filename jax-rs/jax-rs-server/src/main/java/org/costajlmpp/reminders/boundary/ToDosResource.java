package org.costajlmpp.reminders.boundary;

import org.costajlmpp.reminders.entity.ToDo;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonPatch;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Stateless
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Path("todos")
public class ToDosResource {

    @Inject ToDoManager manager;

    @POST
    public Response save (ToDo toDo, @Context UriInfo info) {
        System.out.println("----- On create ---- ");
        ToDo saved = this.manager.save(toDo);
        URI uri = info.getAbsolutePathBuilder().path("{id}").build(saved.getId());
        return Response.created(uri).entity(saved).build();
    }

    @GET
    @Path("{id:\\d+}")
    public ToDo find(@PathParam("id") long id) {
        return this.manager.findById(id);
    }

    @DELETE
    @Path("{id:\\d+}")
    public void delete(@PathParam("id") long id) {
        this.manager.delete(id);
    }

    @PUT
    @Path("{id:\\d+}")
    public Response update(@PathParam("id") long id, ToDo todo) {
        if (this.find(id) == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        todo.setId(id);
        this.manager.save(todo);

        return Response.noContent().build();
    }

    @GET
    public List<ToDo> search() {
        return this.manager.search();
    }

    @PATCH
    @Path("{id}")
    public void updateStatus(@PathParam("id") long id, JsonPatch jsonPatch) {

        //TODO:: missing implementation

        System.out.println(jsonPatch);

    }



}
