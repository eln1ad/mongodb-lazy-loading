package com.projects.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projects.model.Ticket;
import com.projects.repository.TicketRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public List<Ticket> findAllByUserId(String id) {
        return ticketRepository.findAllByUserId(id);
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
}
