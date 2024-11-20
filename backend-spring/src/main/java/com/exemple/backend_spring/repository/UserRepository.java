package com.exemple.backend_spring.repository;

import com.exemple.backend_spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}