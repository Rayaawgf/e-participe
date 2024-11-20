package com.exemple.backend_spring.repository;

import com.exemple.backend_spring.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseRepository extends JpaRepository<Response, Long> {
}