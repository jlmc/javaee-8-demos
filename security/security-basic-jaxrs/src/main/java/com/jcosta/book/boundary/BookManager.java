package com.jcosta.book.boundary;

import com.jcosta.book.entity.Book;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Singleton;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class BookManager {

    List<Book> all() {
        return Arrays.asList(new Book("123", "Lusiadas"));
    }
}
