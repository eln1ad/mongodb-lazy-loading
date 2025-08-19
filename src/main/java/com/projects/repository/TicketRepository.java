package com.projects.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.projects.model.Ticket;

public interface TicketRepository extends MongoRepository<Ticket, String> {
    
}
