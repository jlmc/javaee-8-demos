package org.costajlmpp.book.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

@Entity
@Table(name = "BOOK")

@EntityListeners({
    BookEntityListener.class
})
/*
@NamedQueries({
    @NamedQuery(name = Book.FIND_BY_ISBN, query = "select b from Book b left join fetch b.authors  where b.isbn = :isbn"),
    @NamedQuery(name = Book.FIND_WHERE_PUBLISHING_DATE_AFTER, query = "select b from Book b left join fetch b.authors where b.publishingDate > :publishingDate")
})*/
@NamedQuery(name = Book.FIND_ALL, query = "select distinct b from Book b left join fetch b.authors order by b.isbn")
@NamedQuery(name = Book.FIND_BY_ISBN, query = "select distinct b from Book b left join fetch b.authors  where b.isbn = :isbn")
@NamedQuery(name = Book.FIND_WHERE_PUBLISHING_DATE_AFTER, query = "select distinct b from Book b left join fetch b.authors where b.publishingDate > :publishingDate")
public class Book {

    public static final String FIND_ALL = "org.costajlmpp.book.entity.Book.FIND_ALL";
    public static final String FIND_BY_ISBN = "org.costajlmpp.book.entity.Book.FIND_BY_ISBN";
    public static final String FIND_WHERE_PUBLISHING_DATE_AFTER = "org.costajlmpp.book.entity.Book.FIND_WHERE_PUBLISHING_DATE_AFTER";

    @Id
    @Column(nullable = false, updatable = false)
    private String isbn;
    @Column(length = 150, nullable = false)
    private String title;

    @Column(name = "DESCRIPTION", length = 1024,  nullable = true)
    @Basic(fetch = FetchType.LAZY)
    private String description;

    @Column(name = "PUBLISHING_DATE", nullable = false)
    private LocalDateTime publishingDate;

    @Column(length = 100, nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Embedded
    private Amount price;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE } )
    @JoinTable(name = "BOOK_AUTHOR",
            joinColumns = @JoinColumn(name = "BOOK_ISBN"),
            inverseJoinColumns = @JoinColumn(name = "AUTHOR_ID")
    )
    private Set<Author> authors = new HashSet<>(0);

    public Set<Author> getAuthors() {
        return Collections.unmodifiableSet(authors);
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getPublishingDate() {
        return publishingDate;
    }

    public Amount getPrice() {
        return price;
    }

    public void setPrice(Amount price) {
        this.price = price;
    }


    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
