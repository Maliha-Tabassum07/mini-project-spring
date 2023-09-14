package com.maliha.miniproject.repository;

import com.maliha.miniproject.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    Optional<UserEntity> findByEmail(String email);

    Boolean existsByEmail(String email);

    Optional<UserEntity> findByUserId(Integer user_id);

}
