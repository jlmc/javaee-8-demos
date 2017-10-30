package org.costajlmpp.book.boundary;

import org.costajlmpp.book.entity.Book;
import org.costajlmpp.book.entity.QueryParameter;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("books")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class BookResource {

    @Inject BookManager manager;
    @Context UriInfo uriInfo;

    @GET
    public Response search(
            @QueryParam("gender") String gender,
            @QueryParam("title") String title) {

        final QueryParameter queryParameter = QueryParameter.with("gender", gender).and("title", title);

        GenericEntity<List<Book>> books = new GenericEntity<List<Book>>(this.manager.search(queryParameter)){};

        return Response.ok(books).build();
    }

    @GET
    @Path("publishings")
    public List<String> getBooksPublishings() {
        return manager.getBooksPublishings();
    }

    @POST
    public Response register(@Valid Book book) {
        this.manager.register(book);

        final URI uri = uriInfo.getAbsolutePathBuilder().path("{id}").build(book.getIsbn());

        return Response.created(uri).entity(book).build();
    }

    @GET
    @Path("{isbn}")
    public Response get(@PathParam("isbn") String isbn) {
        Book book = this.manager.get(isbn);
        return Response.ok(book).build();
    }

}
