package com.exemple.backend_spring.dto;


import com.exemple.backend_spring.enums.ContentType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ContentDTO {
    private Long id;
    private String title;
    private String body;
    private LocalDateTime createdDate;
    private ContentType category; // Attribut pour la catégorie
    private List<CommentDTO> comments;
    private List<QuestionDTO> questions;
}

