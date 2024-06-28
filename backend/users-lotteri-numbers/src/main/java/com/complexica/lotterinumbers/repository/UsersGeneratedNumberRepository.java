package com.complexica.lotterinumbers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.complexica.lotterinumbers.model.User;
import com.complexica.lotterinumbers.model.UsersGeneratedNumbers;
import java.util.List;

public interface UsersGeneratedNumberRepository extends JpaRepository<UsersGeneratedNumbers, Long> {
    List<UsersGeneratedNumbers> findByUserOrderByGeneratedAtDesc(User user);
}
