package com.exemple.backend_spring.repository;

import com.exemple.backend_spring.enums.ContentType;
import com.exemple.backend_spring.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Long> {
    List<Content> findByCategory(ContentType category);
}