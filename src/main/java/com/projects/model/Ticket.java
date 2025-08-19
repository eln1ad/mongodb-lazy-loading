package com.projects.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "Tickets")
public class Ticket {
    @Id
    private String id;
    private String title;
}
