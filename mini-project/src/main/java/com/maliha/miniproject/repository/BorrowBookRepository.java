package com.maliha.miniproject.repository;

import com.maliha.miniproject.entity.BorrowBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowBookRepository extends JpaRepository<BorrowBookEntity,Integer> {
}
