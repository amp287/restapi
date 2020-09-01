package com.amp.restapi;

import java.util.Date;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final UserRepository repository;

    AuthController(UserRepository repository) {
        this.repository = repository;
    }
    
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody Auth auth) {
        Optional<User> result = repository.findByEmail(auth.email);
        User user;

        if (result.isEmpty()) {
            System.out.println("Result is empty!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        user = result.get();

        if (!user.getPassword().equals(auth.password)) {
            System.out.println("Passwords do not match! Given: " + auth.password + " expected: " + user.getPassword());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        user.setLastLogin(new Date());

        repository.save(user);

        return ResponseEntity.ok(user);
    }



}