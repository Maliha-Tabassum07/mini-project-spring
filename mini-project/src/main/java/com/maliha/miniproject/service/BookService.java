package com.maliha.miniproject.service;

import com.maliha.miniproject.entity.BookEntity;
import com.maliha.miniproject.model.Book;
import com.maliha.miniproject.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public Book createBook(Book book) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setName(book.getName());
        bookEntity.setAuthor(book.getAuthor());
        bookEntity.setAvailable("available");
        bookEntity.setDescription(book.getDescription());
        bookEntity.setGenre(book.getGenre());
        bookEntity.setUrl(book.getUrl());
        BookEntity storedBookDetails = bookRepository.save(bookEntity);
        Book returnedValue = new ModelMapper().map(storedBookDetails, Book.class);
        return returnedValue;
    }

    public Book updateBook(Book updatedBook) throws RuntimeException {
        BookEntity updatedBookEntity = bookRepository.findById(updatedBook.getBookId()).orElseThrow(()->new NullPointerException());
        if (updatedBookEntity.getAvailable().equals("available") ) {
            updatedBookEntity.setName(updatedBook.getName());
            updatedBookEntity.setDescription(updatedBook.getDescription());
            updatedBookEntity.setAuthor(updatedBook.getAuthor());
            return new ModelMapper().map(bookRepository.save(updatedBookEntity),Book.class);
        } else {throw new RuntimeException();
        }
    }

    public boolean deleteBook(Integer bookId) throws NullPointerException {
        BookEntity deleteBook = bookRepository.findById(bookId).orElseThrow(() -> new NullPointerException());
        if (deleteBook.getAvailable().equals("available")) {
            deleteBook.setName("Deleted book");
            deleteBook.setAuthor("--");
            deleteBook.setGenre("--");
            deleteBook.setGenre("--");
            deleteBook.setAvailable("deleted");
            bookRepository.save(deleteBook);
            return true;
        } else {
            return false;
        }
    }

    public List<BookEntity> getAllBooks() {
        return bookRepository.findAllByAvailableNot("deleted").stream()
                .sorted(Comparator.comparing(BookEntity::getName))
                .collect(Collectors.toList());
    }
    public BookEntity getBookById(Integer bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new NullPointerException());
    }
}