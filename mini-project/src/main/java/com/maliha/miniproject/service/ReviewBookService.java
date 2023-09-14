package com.maliha.miniproject.service;

import com.maliha.miniproject.entity.BookEntity;
import com.maliha.miniproject.entity.ReviewBookEntity;
import com.maliha.miniproject.entity.UserEntity;
import com.maliha.miniproject.model.BorrowBook;
import com.maliha.miniproject.model.ReviewBook;
import com.maliha.miniproject.repository.BookRepository;
import com.maliha.miniproject.repository.ReviewBookRepository;
import com.maliha.miniproject.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewBookService {

    @Autowired
    ReviewBookRepository reviewBookRepository;

    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;


    public List<ReviewBook> getReviews(Integer bookId){
        List<ReviewBook> reviewBookList=new ArrayList<>();
        for(ReviewBookEntity reviewBookEntity:reviewBookRepository.findAllByBook(bookRepository.findById(bookId).orElseThrow(()-> new NullPointerException())).orElseThrow(()->new NullPointerException())){
            reviewBookList.add(new ModelMapper().map(reviewBookEntity,ReviewBook.class));
        }
        return reviewBookList;
    }

    public ReviewBook createReview(ReviewBook reviewBook, Integer bookId){
        ReviewBookEntity reviewBookEntity=new ReviewBookEntity();
        reviewBookEntity.setReview(reviewBook.getReview());
        reviewBookEntity.setRatings(reviewBook.getRatings());
        reviewBookEntity.setBook(bookRepository.findById(bookId).orElseThrow(()-> new NullPointerException()));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity = userRepository.findByEmail(authentication.getName()).orElseThrow(()->new NullPointerException());
        reviewBookEntity.setUser(userEntity);
        return new ModelMapper().map(reviewBookRepository.save(reviewBookEntity), ReviewBook.class);
    }

    public ReviewBook updateReviewBook(ReviewBook reviewBook, Integer bookId, Integer reviewId){
        ReviewBookEntity reviewBookEntity=reviewBookRepository.findById(reviewId).orElseThrow(()->new RuntimeException());
        reviewBookEntity.setReview(reviewBook.getReview());
        reviewBookEntity.setRatings(reviewBook.getRatings());
        ReviewBookEntity savedBook=reviewBookRepository.save(reviewBookEntity);

        return new ModelMapper().map(savedBook,ReviewBook.class);
    }

    public Boolean deleteBookReview(Integer bookId, Integer reviewId){
        if(bookRepository.existsById(bookId)&&reviewBookRepository.existsById(reviewId)){
            reviewBookRepository.deleteById(reviewId);
            return true;
        }
        else
            return false;
    }





}
