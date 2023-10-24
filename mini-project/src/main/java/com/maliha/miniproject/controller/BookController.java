package com.maliha.miniproject.controller;

import com.maliha.miniproject.entity.BookEntity;
import com.maliha.miniproject.model.Book;
import com.maliha.miniproject.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("/create")
    public ResponseEntity<Book> create(@RequestBody Book book){
        return new ResponseEntity<Book>(bookService.createBook(book), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Book> updateBook(@RequestBody Book book) throws NullPointerException{
        return new ResponseEntity<>(bookService.updateBook(book),HttpStatus.ACCEPTED);
    }
    @GetMapping("/all")
    public List<BookEntity> getAllBook(){
       return bookService.getAllBooks();
    }

    @DeleteMapping("/delete/{bookId}")
    public boolean deleteBook(@PathVariable Integer bookId) throws NullPointerException{
        return bookService.deleteBook(bookId);
    }
    @GetMapping("/get/{bookId}")
    public BookEntity getBookById(@PathVariable Integer bookId) throws NullPointerException{
        return bookService.getBookById(bookId);
    }


}
