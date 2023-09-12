package com.maliha.miniproject.repository;

import com.maliha.miniproject.entity.ReviewBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ReviewBookRepository extends JpaRepository<ReviewBookEntity,Integer> {
}
