package com.exemple.backend_spring.dto;

import lombok.Data;

@Data
public class PropositionDTO {
    private Long id;
    private String title;
    private String document;
    private boolean status;
    private Long userId;
}