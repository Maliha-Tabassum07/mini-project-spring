package com.maliha.miniproject.model;

public class ReviewBook {
    private Integer reviewId;
    private String review;
    private float ratings;

    public ReviewBook() {
    }

    public ReviewBook(Integer reviewId,String review, float ratings) {
        this.reviewId = reviewId;
        this.review = review;
        this.ratings = ratings;
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

}
