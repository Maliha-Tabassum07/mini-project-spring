package com.maliha.miniproject.repository;

import com.maliha.miniproject.entity.BorrowBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface BorrowBookRepository extends JpaRepository<BorrowBookEntity,Integer> {
        Optional<BorrowBookEntity> findByBookBookId(Integer bookId);
}
