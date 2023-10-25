package com.maliha.miniproject.controller;

import com.maliha.miniproject.entity.ReviewBookEntity;
import com.maliha.miniproject.model.BorrowBook;
import com.maliha.miniproject.model.ReviewBook;
import com.maliha.miniproject.service.ReviewBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class ReviewBookController {
    @Autowired
    ReviewBookService reviewBookService;
    @GetMapping("/{bookId}/reviews")
    public ResponseEntity<List<ReviewBookEntity>> getReview(@PathVariable Integer bookId){
        return new ResponseEntity<List<ReviewBookEntity>>(reviewBookService.getReviews(bookId), HttpStatus.ACCEPTED);
    }

    @PostMapping("/{bookId}/reviews/create")
    public ResponseEntity<ReviewBook> createReview(@RequestBody ReviewBook reviewBook, @PathVariable Integer bookId ){
        return new ResponseEntity<ReviewBook>(reviewBookService.createReview(reviewBook,bookId), HttpStatus.CREATED);
    }

    @PutMapping("/{bookId}/reviews/{reviewId}/update")
    public ResponseEntity<ReviewBook> updateReview(@RequestBody ReviewBook reviewBook, @PathVariable("bookId") Integer bookId,@PathVariable("reviewId") Integer reviewId ){
        return new ResponseEntity<ReviewBook>(reviewBookService.updateReviewBook(reviewBook,bookId,reviewId), HttpStatus.CREATED);
    }

    @DeleteMapping("/{bookId}/reviews/{reviewId}/delete")
    public Boolean deleteReview(@PathVariable("bookId") Integer bookId,@PathVariable("reviewId") Integer reviewId){
        return reviewBookService.deleteBookReview(bookId,reviewId);
    }

}
