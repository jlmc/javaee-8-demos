package org.jcosta.book.entity;

import javax.persistence.*;

/**
 * @author costa
 * on 06/10/2017.
 */
@Entity
@Table(name = "book")
@NamedQuery(name = Book.FIND_BY_ISBN, query = "select b from Book b where b.isbn = :isbn")

public class Book {

    public static final String FIND_BY_ISBN = "org.jcosta.book.entity.Book.find_by_isbn";

    @Id
    @Column(nullable = false, updatable = false)
    private String isbn;
    @Column(length = 150, nullable = false)
    private String name;

    @Column(name = "description", length = 1024,  nullable = true)
    @Basic(fetch = FetchType.LAZY)
    private String description;

    @Embedded
    private Amount price;

    public String getName() {
        return name;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getDescription() {
        return description;
    }

    public Amount getPrice() {
        return price;
    }
}
