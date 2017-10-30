package org.jcosta.book.control;

import org.jcosta.book.entity.Book;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class ReportGenerator {

    @PersistenceContext
    EntityManager manager;

    @Inject
    Logger logger;


    public void generate () {

        final Stream<Book> allBooksAsStream = this.manager.createQuery(Book.FIND_ALL, Book.class).getResultStream();

        // this => is supose to generate some CSV file in a remote location
        allBooksAsStream.forEachOrdered(System.out::println);
    }
}
