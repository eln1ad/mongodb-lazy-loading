package com.projects.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.projects.model.Ticket;

public interface TicketRepository extends MongoRepository<Ticket, String> {
    List<Ticket> findAllByUserId(String id);
    @Aggregation(pipeline = {
        "{ $addFields: { convertedUserId: { $toObjectId: '$userId' } } }",
        "{ $lookup: { from: 'Users', localField: 'convertedUserId', foreignField: '_id', as: 'user' } }",
        "{ $unwind: '$user' }",
        "{ $match: { 'user.login': ?0 } }"
    })
    List<Ticket> findAllByUserLogin(String login);
}
