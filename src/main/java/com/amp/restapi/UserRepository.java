package com.amp.restapi;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    public Optional<User> findById(String id);
    public List<User> findAll();
    public void deleteById(String id);
    public Optional<User> findByEmail(String email);
}