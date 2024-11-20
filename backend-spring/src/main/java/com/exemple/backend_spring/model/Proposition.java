package com.exemple.backend_spring.model;

import lombok.Data;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Proposition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title; // Titre de la proposition

    private LocalDateTime publicationDate; // Date de publication

    private boolean status=false; // État de la proposition (true pour validé, false pour non validé)

    private LocalDateTime validationDate; // Date de validation

    private String document; // Chemin ou nom du fichier téléchargé

    @Column(name = "user_id")
    private Long userId; // ID de l'utilisateur qui a proposé
}
