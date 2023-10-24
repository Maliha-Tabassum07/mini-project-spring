package com.maliha.miniproject.controller;

import com.maliha.miniproject.entity.BorrowBookEntity;
import com.maliha.miniproject.model.BorrowBook;
import com.maliha.miniproject.service.BorrowBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BorrowBookController {
    @Autowired
    BorrowBookService borrowBookService;
    @PostMapping("/{bookId}/borrow")
    public ResponseEntity<BorrowBook> borrow(@PathVariable Integer bookId){
        return new ResponseEntity<BorrowBook>(borrowBookService.borrowBook(bookId),HttpStatus.ACCEPTED);
    }
    @PutMapping("/{bookId}/return")
    public ResponseEntity<BorrowBookEntity> returnBook(@PathVariable Integer bookId){
          return new ResponseEntity<>(borrowBookService.returnBook(bookId),HttpStatus.ACCEPTED);
    }
}
