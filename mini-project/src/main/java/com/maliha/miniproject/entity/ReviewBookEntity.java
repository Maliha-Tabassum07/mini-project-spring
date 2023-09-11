package com.maliha.miniproject.entity;

public class ReviewBookEntity {
    private Integer review_id;
    private String review;
    private float ratings;
    private Integer book_id;
    private Integer user_id;

    public ReviewBookEntity() {
    }

    public ReviewBookEntity(Integer review_id, String review, float ratings, Integer book_id, Integer user_id) {
        this.review_id = review_id;
        this.review = review;
        this.ratings = ratings;
        this.book_id = book_id;
        this.user_id = user_id;
    }

    public Integer getReview_id() {
        return review_id;
    }

    public void setReview_id(Integer review_id) {
        this.review_id = review_id;
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

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
