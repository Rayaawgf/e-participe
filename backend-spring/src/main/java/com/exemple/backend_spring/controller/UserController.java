package com.exemple.backend_spring.controller;



import com.exemple.backend_spring.dto.UserDTO;
import com.exemple.backend_spring.model.User;
import com.exemple.backend_spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser (@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setNumber(userDTO.getNumber());
        user.setPassword(userDTO.getPassword());

        User savedUser  = userService.registerUser (user);
        return ResponseEntity.ok(savedUser );
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> selectAllUsers() {
        List<User> users = userService.selectAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> editUser (@PathVariable Long id, @RequestBody UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setNumber(userDTO.getNumber());
        user.setPassword(userDTO.getPassword());

        Optional<User> updatedUser  = userService.editUser (id, user);
        return updatedUser .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser (@PathVariable Long id) {
        userService.deleteUser (id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser (@RequestBody UserDTO userDTO) {
        Optional<User> user = userService.loginUser (userDTO.getNumber(), userDTO.getPassword());
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(401).build()); // Unauthorized
    }
}