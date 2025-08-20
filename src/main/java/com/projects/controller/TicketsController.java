package com.projects.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projects.model.Ticket;
import com.projects.service.TicketService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tickets")
public class TicketsController {
    private final TicketService ticketService;

    @GetMapping
    public List<Ticket> getTickets(@RequestParam(required = false, name = "user_id") String id, @RequestParam(required = false, name = "user_login") String login) throws Exception {
        if (id != null) {
            return ticketService.findAllByUserId(id);
        }
        if (login != null) {
            return ticketService.findAllByUserLogin(login);
        }
        return ticketService.findAll();
    }

    @GetMapping("/{id}")
    public Ticket getTicketById(@PathVariable String id) throws Exception {
        return ticketService.findById(id);
    }

    @PostMapping
    public Ticket postTicket(@RequestBody Ticket ticket) {
        return ticketService.create(ticket);
    }

    @PutMapping
    public void putTicketById(@PathVariable String id, @RequestBody Ticket ticket) throws Exception {
        ticketService.updateById(id, ticket);
    }
}
