package com.jcosta.book.boundary;

import com.jcosta.book.entity.Book;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path("books")
public class BookResources {

    @Inject
    BookManager manager;

    @GET
    public List<Book> books() {
        return manager.all();
    }


}
