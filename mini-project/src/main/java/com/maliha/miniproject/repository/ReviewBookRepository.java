package com.maliha.miniproject.repository;

import com.maliha.miniproject.entity.BookEntity;
import com.maliha.miniproject.entity.BorrowBookEntity;
import com.maliha.miniproject.entity.ReviewBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface ReviewBookRepository extends JpaRepository<ReviewBookEntity,Integer> {
    Optional<ReviewBookEntity> findByBook(BookEntity book);


}
