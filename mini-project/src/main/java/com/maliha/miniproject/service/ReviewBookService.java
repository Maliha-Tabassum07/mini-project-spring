package com.maliha.miniproject.service;

import com.maliha.miniproject.repository.ReviewBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewBookService {

    @Autowired
    ReviewBookRepository reviewBookRepository;



}
