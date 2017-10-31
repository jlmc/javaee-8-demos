package org.costajlmpp.account.boundary;

import org.costajlmpp.account.entity.Account;
import org.costajlmpp.account.entity.Moviment;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("accounts")
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {

    @Inject
    AccountManager manager;
    @Context
    UriInfo uriInfo;

    @GET
    public Response get() {
        final List<Account> accounts = this.manager.search();

        GenericEntity<List<Account>> entitys = new GenericEntity<List<Account>>(accounts) {};

        return Response.ok(entitys).build();
    }

    @POST
    public Response create (Account account) {
        Account newAccount = this.manager.create(account);

        final URI uri = uriInfo.getAbsolutePathBuilder().path("{id}").build(newAccount.getId());

        return Response.created(uri).entity(newAccount).build();
    }

    @PUT
    @Path("{id}")
    public Response addMoviment(@PathParam("id") String id, Moviment moviment) {
        this.manager.addMoviment(id, moviment);
        return Response.noContent().build();
    }



}
