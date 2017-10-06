package org.jcosta.book.entity;

import javax.persistence.*;

/**
 * @author costa
 * on 06/10/2017.
 */
@Entity
@Table(name = "book")
public class Book {

    @Id
    @Column(nullable = false, updatable = false)
    private Long isbn;
    @Column(length = 150, nullable = false)
    private String name;

    private String description;

    public String getName() {
        return name;
    }

    public Long getIsbn() {
        return isbn;
    }

    public String getDescription() {
        return description;
    }
}
