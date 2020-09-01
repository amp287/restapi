package com.amp.restapi;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserRepository repository;

    UserController(UserRepository repository) {
        this.repository = repository;
    }
    
    @GetMapping("/users/list")
	public List<User> list() {
        return repository.findAll();
    }
    
    @PostMapping("/users")
    public ResponseEntity<User> newUser(@RequestBody User user) {
        User new_user;
        
        try {
            new_user = (User)repository.save(user);
        } catch(org.springframework.dao.DuplicateKeyException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        
        return ResponseEntity.ok(new_user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody UserUpdate userUpdate) {
        Optional<User> result = repository.findById(id);
        User user;

        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        user = result.get();

        user.setName(userUpdate.name);
        user.setPassword(userUpdate.password);

        repository.save(user);

        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable String id) {
        repository.deleteById(id);
    }
}