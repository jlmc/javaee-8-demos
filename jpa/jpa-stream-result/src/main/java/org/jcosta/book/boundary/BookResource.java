package org.jcosta.book.boundary;

import org.jcosta.book.entity.Book;

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
    public Response search() {

        GenericEntity<List<Book>> books = new GenericEntity<List<Book>>(this.manager.search()){};

        return Response.ok(books).build();
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
