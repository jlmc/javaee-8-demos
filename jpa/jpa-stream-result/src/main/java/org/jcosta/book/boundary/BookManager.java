package org.jcosta.book.boundary;

import org.jcosta.book.entity.Book;
import org.jcosta.book.entity.QueryParameter;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Stateless
public class BookManager {

    @PersistenceContext
    EntityManager manager;

    @Inject
    Logger logger;

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Book> all() {
        return this.manager
                .createQuery("select b from Book b left join fetch b.authors", Book.class)
                .getResultList();
    }

    public List<String> getBooksPublishings() {
        Stream<Book> books = this.manager.createQuery("SELECT b FROM Book b", Book.class).getResultStream();
        return books.map(b -> b.getTitle() + " was published on " + b.getPublishingDate()).collect(Collectors.toList());
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Book register(Book book) {
        this.manager.persist(book);
        this.manager.flush();
        return book;
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Book get(String isbn) {
        return this.manager.createNamedQuery(Book.FIND_BY_ISBN, Book.class)
                .setParameter("isbn", isbn)
                .getSingleResult();
    }

    public List<Book> search(QueryParameter parameters) {
        final CriteriaBuilder builder = this.manager.getCriteriaBuilder();

        final CriteriaQuery<Book> criteria = builder.createQuery(Book.class);

        final Root<Book> bookRoot = criteria.from(Book.class);
        bookRoot.fetch("authors", JoinType.LEFT);

        List<Predicate> predicates = new ArrayList<>();
        if (parameters.contains("gender")) {
            final String gender = parameters.get("gender");
            String partial = gender.toUpperCase() + "%";
            final Predicate predicate = builder.like(builder.upper(bookRoot.get("gender")), partial);
            predicates.add(predicate);
        }

        if (parameters.contains("title")) {
            String partial = parameters.get("title") + "%";
            final Predicate predicate = builder.like(builder.upper(bookRoot.get("title")), partial.toUpperCase());
            predicates.add(predicate);
        }


        criteria.select(bookRoot).where(predicates.toArray(new Predicate[0]));

        return manager.createQuery(criteria).getResultList();
    }
}
