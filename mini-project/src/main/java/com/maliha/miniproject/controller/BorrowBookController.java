package com.maliha.miniproject.controller;

import com.maliha.miniproject.model.BorrowBook;
import com.maliha.miniproject.service.BorrowBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BorrowBookController {
    @Autowired
    BorrowBookService borrowBookService;

    @PostMapping("/books/{bookId}/borrow")
    public ResponseEntity<BorrowBook> borrow(@PathVariable Integer bookId){
        return new ResponseEntity<BorrowBook>(borrowBookService.borrowBook(bookId),HttpStatus.ACCEPTED);
    }

    @PutMapping("/books/{bookId}/return")
    public Boolean returnBook(@PathVariable Integer bookId){
        if(borrowBookService.returnBook(bookId))
            return true;
        else
            return false;
    }
}
