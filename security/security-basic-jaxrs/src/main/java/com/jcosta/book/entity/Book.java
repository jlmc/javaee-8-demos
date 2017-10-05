package com.jcosta.book.entity;

public class Book {

    private String isbn;
    private String name;

    public Book() {}

    public Book(String isbn, String name) {
        this.isbn = isbn;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getIsbn() {
        return isbn;
    }

}
