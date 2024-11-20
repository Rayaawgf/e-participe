package com.exemple.backend_spring.model;



import jakarta.persistence.*;


import lombok.Data;

import java.util.Collection;


@Data
@Entity
@Table(name = "users")
public class User {
    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String number;

    @ElementCollection(fetch = FetchType.EAGER)
    private Collection<String> roles;

    private String password;


}