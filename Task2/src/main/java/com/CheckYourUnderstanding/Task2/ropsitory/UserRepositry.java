package com.CheckYourUnderstanding.Task2.ropsitory;

import com.CheckYourUnderstanding.Task2.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositry extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByUsername(String username);
}

