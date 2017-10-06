package org.jcosta.book.boundary;

import org.jcosta.book.entity.Book;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Stateless
public class BookManager {

    @PersistenceContext
    EntityManager manager;

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Book> search() {
        return this.manager.createQuery("select b from Book b", Book.class).getResultList();
    }

    public Book registe(Book book) {
        this.manager.persist(book);
        return book;
    }
}
