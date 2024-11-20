package com.exemple.backend_spring.model;
import com.exemple.backend_spring.enums.ContentType;
import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String body;

    private LocalDateTime createdDate;

    @Enumerated(EnumType.STRING)
    private ContentType category; // Attribut pour la catégorie

    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL)
    private List<Question> questions;
}