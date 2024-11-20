package com.exemple.backend_spring.repository;

import com.exemple.backend_spring.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByContentId(Long contentId);
}
