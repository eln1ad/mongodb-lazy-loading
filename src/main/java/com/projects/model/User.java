package com.projects.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.Data;

@Data
@Document(collection = "Users")
public class User {
    @Id
    private String id;
    private String login;
    private String password;

    @DocumentReference(lazy = true, lookup = "{ 'userId': ?#{#self._id} }")
    List<Ticket> tickets;
}
