package com.exemple.backend_spring.model;


import com.exemple.backend_spring.enums.ResponseType;
import lombok.Data;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionText;

    @ManyToOne
    @JoinColumn(name = "content_id")
    private Content content;

    @Enumerated(EnumType.STRING)
    private ResponseType responseType; // Type de réponse pour la question

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Response> responses;
}
