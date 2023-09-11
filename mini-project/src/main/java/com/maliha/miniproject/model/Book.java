package com.maliha.miniproject.model;

public class Book {
    private Integer book_id;
    private String name;
    private String author;
    private String available;
    private String description;

    public Book() {
    }

    public Book(Integer book_id, String name, String author, String available, String description) {
        this.book_id = book_id;
        this.name = name;
        this.author = author;
        this.available = available;
        this.description = description;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
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

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
