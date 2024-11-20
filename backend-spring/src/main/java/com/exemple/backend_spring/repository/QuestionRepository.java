package com.exemple.backend_spring.repository;

import com.exemple.backend_spring.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}