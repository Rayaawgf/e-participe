package com.exemple.backend_spring.service;


import com.exemple.backend_spring.model.User;
import com.exemple.backend_spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User registerUser (User user) {
        return userRepository.save(user);
    }

    public List<User> selectAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> editUser (Long id, User userDetails) {
        return userRepository.findById(id).map(user -> {
            user.setName(userDetails.getName());
            user.setNumber(userDetails.getNumber());
            user.setPassword(userDetails.getPassword());
            return userRepository.save(user);
        });
    }

    public void deleteUser (Long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> loginUser (String number, String password) {
        return userRepository.findAll().stream()
                .filter(user -> user.getNumber().equals(number) && user.getPassword().equals(password))
                .findFirst();
    }
}