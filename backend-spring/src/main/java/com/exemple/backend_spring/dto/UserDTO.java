package com.exemple.backend_spring.dto;


public class UserDTO {
    private String name;
    private String number;
    private String password;

    // Constructeur par défaut
    public UserDTO() {
    }

    // Constructeur avec paramètres
    public UserDTO(String name, String number, String password) {
        this.name = name;
        this.number = number;
        this.password = password;
    }

    // Getters et Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}