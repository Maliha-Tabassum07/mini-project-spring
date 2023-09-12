package com.maliha.miniproject.service;

import com.maliha.miniproject.entity.BookEntity;
import com.maliha.miniproject.model.Book;
import com.maliha.miniproject.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private ModelMapper modelMapper = new ModelMapper();
    ;

    @Autowired
    BookRepository bookRepository;

    public Book createBook(Book book) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setName(book.getName());
        bookEntity.setAuthor(book.getAuthor());
        bookEntity.setAvailable(book.getAvailable());
        bookEntity.setDescription(book.getDescription());
        BookEntity storedBookDetails = bookRepository.save(bookEntity);
        Book returnedValue = modelMapper.map(storedBookDetails, Book.class);
        return returnedValue;
    }

    public Book updateBook(Book updatedBook) throws NullPointerException {
        BookEntity updatedBookEntity = modelMapper.map(updatedBook, BookEntity.class);
        if (bookRepository.existsById(updatedBookEntity.getBookId()) && bookRepository.findById(updatedBookEntity.getBookId()).get().getAvailable() == "Available") {
            return modelMapper.map(bookRepository.save(updatedBookEntity), Book.class);
        } else throw new NullPointerException();
    }

    public boolean deleteBook(Integer bookId) throws NullPointerException {
        BookEntity deleteBook = bookRepository.findById(bookId).orElseThrow(() -> new NullPointerException());
        if (deleteBook.getAvailable().equals("Borrowed") && deleteBook.getAvailable().equals("Deleted")) {
            return false;
        } else {
            deleteBook.setAvailable("Deleted");
            return true;
        }
    }

    public List<BookEntity> getAllBooks() {
        return bookRepository.findAll();
    }
}