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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowBookService {

    @Autowired
    BorrowBookRepository borrowBookRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;


    public BorrowBook borrowBook(Integer bookId)throws NullPointerException{
        BorrowBookEntity borrowBookEntity = new BorrowBookEntity();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity = userRepository.findByEmail(authentication.getName()).orElseThrow(()->new NullPointerException());

        BookEntity bookEntity=bookRepository.findById(bookId).orElseThrow(() -> new NullPointerException());
        if(bookEntity.getAvailable().equals("borrowed")||bookEntity.getAvailable().equals("deleted")){
            throw new NullPointerException();
        }
        else {
          bookEntity.setAvailable("borrowed");
            bookRepository.save(bookEntity);
            borrowBookEntity.setDueDate(LocalDate.now().plusDays(7));
            borrowBookEntity.setUser(userEntity);
            borrowBookEntity.setBook(bookEntity);
            return new ModelMapper().map(borrowBookRepository.save(borrowBookEntity),BorrowBook.class);
        }
    }

    public BorrowBookEntity returnBook(Integer bookId)throws NullPointerException{
        BookEntity bookEntity=bookRepository.findById(bookId).orElseThrow(() -> new NullPointerException());
        System.out.println("printing");
        BorrowBookEntity borrowBookEntity=borrowBookRepository.findByBookAndReturnDateIsNull(bookEntity).orElseThrow(() -> new NullPointerException());
        if(bookEntity.getAvailable().equals("borrowed")){
            bookEntity.setAvailable("available");
            bookRepository.save(bookEntity);
            borrowBookEntity.setReturnDate(LocalDate.now());
            return borrowBookRepository.save(borrowBookEntity);

        }
        throw new NullPointerException();
    }
    public List<BorrowBook> borrowBookAllWithUserId(Integer userId){
        List<BorrowBook> borrowBookList=new ArrayList<>();
        for(BorrowBookEntity borrowBookEntity:borrowBookRepository.findAllByUser(userRepository.findByUserId(userId).orElseThrow(() -> new NullPointerException())).orElseThrow(() -> new NullPointerException())){
            borrowBookList.add(new ModelMapper().map(borrowBookEntity,BorrowBook.class));
        }
        return borrowBookList;
    }
    public List<BorrowBookEntity> getHistory(Integer userId){
        return borrowBookRepository.findAllByUser(userRepository.findByUserId(userId).orElseThrow(() -> new NullPointerException())).orElseThrow(() -> new NullPointerException());
    }
    public List<BorrowBookEntity> getBorrowedBook(Integer userId){
        return borrowBookRepository.findAllByUserAndReturnDateIsNull(userRepository.findByUserId(userId).orElseThrow(() -> new NullPointerException())).orElseThrow(() -> new NullPointerException());
    }
}
