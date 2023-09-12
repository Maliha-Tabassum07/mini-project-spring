package com.maliha.miniproject.service;

import com.maliha.miniproject.entity.ReviewBookEntity;
import com.maliha.miniproject.model.BorrowBook;
import com.maliha.miniproject.model.ReviewBook;
import com.maliha.miniproject.repository.BookRepository;
import com.maliha.miniproject.repository.ReviewBookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class ReviewBookService {

    @Autowired
    ReviewBookRepository reviewBookRepository;

    @Autowired
    BookRepository bookRepository;

    ModelMapper modelMapper=new ModelMapper();

    public ReviewBook getReviews(Integer bookId){
        return modelMapper.map(reviewBookRepository.findByBookBookId(bookId).orElseThrow(()-> new NullPointerException()), ReviewBook.class);
    }

    public ReviewBook createReview(ReviewBook reviewBook, Integer bookId){
        ReviewBookEntity reviewBookEntity=new ReviewBookEntity();
        reviewBookEntity.setReview(reviewBook.getReview());
        reviewBookEntity.setRatings(reviewBook.getRatings());
        reviewBookEntity.setBook(bookRepository.findById(bookId).orElseThrow(()-> new NullPointerException()));
        //setUser
        return modelMapper.map(reviewBookRepository.save(reviewBookEntity), ReviewBook.class);
    }

    public ReviewBook updateReviewBook(ReviewBook reviewBook, Integer bookId, Integer reviewId){
        ReviewBookEntity reviewBookEntity=reviewBookRepository.findById(reviewId).orElseThrow(()->new RuntimeException());
        return null;
    }

    public Boolean deleteBook(){
        return true;
    }





}
