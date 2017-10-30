package org.costajlmpp.book.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AUTHOR")
public class Author {

    @Id
    private Long id;
    private String name;
    private String about;

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public String getAbout() {
        return about;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
