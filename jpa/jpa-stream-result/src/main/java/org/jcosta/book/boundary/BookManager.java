package org.jcosta.book.boundary;

import org.jcosta.book.entity.Book;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class BookManager {

    @PersistenceContext
    EntityManager manager;

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Book> search() {
        return this.manager
                .createQuery("select b from Book b", Book.class).getResultList();
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
}
