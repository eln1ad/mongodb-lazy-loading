package com.projects.service;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.ConvertOperators;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.projects.model.Ticket;
import com.projects.model.User;
import com.projects.repository.TicketRepository;
import com.projects.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final MongoTemplate mongoTemplate;

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public List<Ticket> findAllByUserId(String id) {
        return ticketRepository.findAllByUserId(id);
    }

    public List<Ticket> findAllByUserLogin(String login) {
        TypedAggregation<Ticket> aggregation = Aggregation.newAggregation(Ticket.class,
            Aggregation.addFields().addFieldWithValue("convertedUserId", ConvertOperators.ToObjectId.toObjectId("$userId")).build(),
            Aggregation.lookup().from("Users").localField("convertedUserId").foreignField("_id").as("user"),
            Aggregation.unwind("user"),
            Aggregation.match(Criteria.where("user.login").is(login))
        );
        return mongoTemplate.aggregate(aggregation, Ticket.class).getMappedResults();
    }

    public Ticket findById(String id) throws Exception {
        return ticketRepository.findById(id).orElseThrow(() -> new Exception());
    }

    public void updateById(String id, Ticket ticket) throws Exception {
        Ticket oldTicket = ticketRepository.findById(id).orElseThrow(() -> new Exception());

        if (ticket.getTitle() != null && ticket.getTitle() != "") {
            oldTicket.setTitle(ticket.getTitle());
        }

        ticketRepository.save(ticket);
    }

    public Ticket create(Ticket ticket) {
        return ticketRepository.insert(ticket);
    }

    public void addTicketToUser(String id, Ticket ticket) throws Exception {
        ticket.setUserId(id);
        User user = userRepository.findById(id).orElseThrow(() -> new Exception());
        user.getTickets().add(ticket);
        ticketRepository.save(ticket);
        userRepository.save(user);
    }
}
