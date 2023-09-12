package com.maliha.miniproject.service;

import com.maliha.miniproject.entity.BookEntity;
import com.maliha.miniproject.entity.BorrowBookEntity;
import com.maliha.miniproject.model.BorrowBook;
import com.maliha.miniproject.repository.BookRepository;
import com.maliha.miniproject.repository.BorrowBookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
@Service
public class BorrowBookService {

    @Autowired
    BorrowBookRepository borrowBookRepository;

    @Autowired
    BookRepository bookRepository;

    ModelMapper modelMapper=new ModelMapper();

    public BorrowBook borrowBook(Integer bookId)throws NullPointerException{
        BorrowBookEntity borrowBookEntity = new BorrowBookEntity();
        BookEntity bookEntity=bookRepository.findById(bookId).orElseThrow(() -> new NullPointerException());
        if(bookEntity.getAvailable().equals("borrowed")&&bookEntity.getAvailable().equals("deleted")){
            throw new NullPointerException();
        }
        else {
          bookEntity.setAvailable("borrowed");
            bookRepository.save(bookEntity);
            borrowBookEntity.setDueDate(LocalDate.now().plusDays(7));
            //save user
            borrowBookEntity.setBook(bookEntity);
            return modelMapper.map(borrowBookRepository.save(borrowBookEntity),BorrowBook.class);
        }
    }

    public Boolean returnBook(Integer bookId){
        BookEntity bookEntity=bookRepository.findById(bookId).orElseThrow(() -> new NullPointerException());
        BorrowBookEntity borrowBookEntity=borrowBookRepository.findByBookBookId(bookId).orElseThrow(() -> new NullPointerException());
        if(bookEntity.getAvailable().equals("borrowed")){
            bookEntity.setAvailable("available");
            bookRepository.save(bookEntity);
            borrowBookEntity.setReturnDate(LocalDate.now());
            return true;
        }
        else {
            return false;
        }
    }
}
