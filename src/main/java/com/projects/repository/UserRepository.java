package com.projects.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.projects.model.User;

public interface UserRepository extends MongoRepository<User, String> {
    
}
