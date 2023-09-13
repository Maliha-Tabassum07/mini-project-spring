package com.maliha.miniproject.service;

import com.maliha.miniproject.entity.BookEntity;
import com.maliha.miniproject.entity.BorrowBookEntity;
import com.maliha.miniproject.entity.UserEntity;
import com.maliha.miniproject.model.BorrowBook;
import com.maliha.miniproject.repository.BookRepository;
import com.maliha.miniproject.repository.BorrowBookRepository;
import com.maliha.miniproject.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class BorrowBookService {

    @Autowired
    BorrowBookRepository borrowBookRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    ModelMapper modelMapper=new ModelMapper();

    public BorrowBook borrowBook(Integer bookId)throws NullPointerException{
        BorrowBookEntity borrowBookEntity = new BorrowBookEntity();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity = userRepository.findByEmail(authentication.getName()).orElseThrow(()->new NullPointerException());
        BookEntity bookEntity=bookRepository.findById(bookId).orElseThrow(() -> new NullPointerException());
        if(bookEntity.getAvailable().equals("borrowed")&&bookEntity.getAvailable().equals("deleted")){
            throw new NullPointerException();
        }
        else {
          bookEntity.setAvailable("borrowed");
            bookRepository.save(bookEntity);
            borrowBookEntity.setDueDate(LocalDate.now().plusDays(7));
            borrowBookEntity.setUser(userEntity);
            borrowBookEntity.setBook(bookEntity);
            return modelMapper.map(borrowBookRepository.save(borrowBookEntity),BorrowBook.class);
        }
    }

    public Boolean returnBook(Integer bookId){
        BookEntity bookEntity=bookRepository.findById(bookId).orElseThrow(() -> new NullPointerException());
        System.out.println("printing");
        BorrowBookEntity borrowBookEntity=borrowBookRepository.findByBook(bookEntity).orElseThrow(() -> new NullPointerException());
        if(bookEntity.getAvailable().equals("borrowed")){
            bookEntity.setAvailable("available");
            bookRepository.save(bookEntity);
            borrowBookEntity.setReturnDate(LocalDate.now());
            borrowBookRepository.save(borrowBookEntity);
            return true;
        }
        else {
            return false;
        }
    }
}
