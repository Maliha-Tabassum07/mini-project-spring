package com.maliha.miniproject.repository;

import com.maliha.miniproject.entity.BookEntity;
import com.maliha.miniproject.entity.BorrowBookEntity;
import com.maliha.miniproject.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface BorrowBookRepository extends JpaRepository<BorrowBookEntity,Integer> {
        Optional<BorrowBookEntity> findByBook(BookEntity book);

        Optional<List<BorrowBookEntity>> findAllByUser(UserEntity userEntity);

        Optional<List<BorrowBookEntity>> findAllByUserAndReturnDateIsNull(UserEntity userEntity);
}
