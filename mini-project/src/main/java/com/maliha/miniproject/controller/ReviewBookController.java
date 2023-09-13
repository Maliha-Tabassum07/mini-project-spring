package com.maliha.miniproject.controller;

import com.maliha.miniproject.model.BorrowBook;
import com.maliha.miniproject.model.ReviewBook;
import com.maliha.miniproject.service.ReviewBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReviewBookController {
    @Autowired
    ReviewBookService reviewBookService;

    @GetMapping("/books/{bookId}/reviews")
    public ResponseEntity<ReviewBook> getReview(@PathVariable Integer bookId){
        return new ResponseEntity<ReviewBook>(reviewBookService.getReviews(bookId), HttpStatus.ACCEPTED);
    }

    @PostMapping("/books/{bookId}/reviews/create")
    public ResponseEntity<ReviewBook> createReview(@RequestBody ReviewBook reviewBook, @PathVariable Integer bookId ){
        return new ResponseEntity<ReviewBook>(reviewBookService.createReview(reviewBook,bookId), HttpStatus.CREATED);
    }

    @PutMapping("/books/{bookId}/reviews/{reviewId}/update")
    public ResponseEntity<ReviewBook> updateReview(@RequestBody ReviewBook reviewBook, @PathVariable("bookId") Integer bookId,@PathVariable("reviewId") Integer reviewId ){
        return new ResponseEntity<ReviewBook>(reviewBookService.updateReviewBook(reviewBook,bookId,reviewId), HttpStatus.CREATED);
    }

    @DeleteMapping("/books/{bookId}/reviews/{reviewId}/delete")
    public Boolean deleteReview(@PathVariable("bookId") Integer bookId,@PathVariable("reviewId") Integer reviewId){
        return reviewBookService.deleteBookReview(bookId,reviewId);
    }

}
