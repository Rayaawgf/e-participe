package com.exemple.backend_spring.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private String text;
    private Long userId; // Référence à l'utilisateur
    private Long contentId; // Référence au contenu
}