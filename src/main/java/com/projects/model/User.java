package com.projects.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.Data;

@Data
@Document(collection = "Users")
public class User {
    @Id
    private String login;
    private String password;

    @ReadOnlyProperty
    @DocumentReference(lazy = true)
    List<Ticket> tickets;
}
