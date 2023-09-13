package com.maliha.miniproject.repository;

import com.maliha.miniproject.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BookRepository extends JpaRepository<BookEntity,Integer> {

}
