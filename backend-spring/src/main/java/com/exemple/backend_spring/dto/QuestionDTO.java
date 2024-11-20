package com.exemple.backend_spring.dto;

import com.exemple.backend_spring.enums.ResponseType;
import lombok.Data;

import java.util.List;

@Data
public class QuestionDTO {
    private Long id;
    private String questionText;
    private Long contentId; // ID du contenu associé
    private ResponseType responseType; // Type de réponse pour la question
    private List<Long> responseIds; // Liste des IDs des réponses associées
}