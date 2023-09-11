package com.maliha.miniproject.repository;

import com.maliha.miniproject.entity.ReviewBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewBookRepository extends JpaRepository<ReviewBookEntity,Integer> {
}
