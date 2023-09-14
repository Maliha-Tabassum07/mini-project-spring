package com.maliha.miniproject.model;

public class Book {
    private Integer bookId;
    private String name;
    private String author;
    private String description;

    public Book() {
    }

    public Book(Integer bookId, String name, String author, String description) {
        this.bookId = bookId;
        this.name = name;
        this.author = author;
        this.description = description;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
