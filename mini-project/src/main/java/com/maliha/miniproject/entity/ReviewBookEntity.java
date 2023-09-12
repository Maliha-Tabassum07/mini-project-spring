package com.maliha.miniproject.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "review_book")
public class ReviewBookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewId;
    private String review;
    private float ratings;
    @ManyToOne
    private BookEntity book;

    @ManyToOne
    private UserEntity user;

    public ReviewBookEntity() {
    }

    public ReviewBookEntity(Integer reviewId, String review, float ratings, BookEntity book, UserEntity user) {
        this.reviewId = reviewId;
        this.review = review;
        this.ratings = ratings;
        this.book = book;
        this.user = user;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public float getRatings() {
        return ratings;
    }

    public void setRatings(float ratings) {
        this.ratings = ratings;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
