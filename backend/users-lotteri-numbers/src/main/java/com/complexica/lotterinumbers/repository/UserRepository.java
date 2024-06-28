package com.complexica.lotterinumbers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.complexica.lotterinumbers.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNameIgnoreCase(String name);
}
