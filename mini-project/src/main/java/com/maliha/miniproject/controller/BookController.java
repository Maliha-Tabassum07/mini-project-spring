package com.maliha.miniproject.controller;

import com.maliha.miniproject.entity.BookEntity;
import com.maliha.miniproject.model.Book;
import com.maliha.miniproject.model.User;
import com.maliha.miniproject.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("/books/create")
    public ResponseEntity<Book> create(@RequestBody Book book){
        return new ResponseEntity<Book>(bookService.createBook(book), HttpStatus.CREATED);
    }

    @PutMapping("/update/book")
    public boolean updateBook(@RequestBody Book book) throws NullPointerException{
        bookService.updateBook(book);
        return true;
    }

    @GetMapping("/books/all")
    public List<BookEntity> getAllBook(){
       return bookService.getAllBooks();
    }

    @DeleteMapping("/books/delete/{bookId}")
    public boolean deleteBook(@RequestBody Integer bookId) throws NullPointerException{
        bookService.deleteBook(bookId);
        return true;
    }


}
