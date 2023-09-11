package com.maliha.miniproject.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "book")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer book_id;
    private String name;
    private String author;
    private String available;
    private String description;
    @OneToMany
    private List<BorrowBookEntity> borrowBookEntityList;

    public BookEntity() {
    }

    public BookEntity(Integer book_id, String name, String author, String available, String description) {
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
