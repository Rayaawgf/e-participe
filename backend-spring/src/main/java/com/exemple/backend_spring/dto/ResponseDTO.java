package com.exemple.backend_spring.dto;

import lombok.Data;

@Data
public class ResponseDTO {
    private Long id;
    private String answerText;
    private Long questionId; // Référence à la question
    private Long userId; // Référence à l'utilisateur
}