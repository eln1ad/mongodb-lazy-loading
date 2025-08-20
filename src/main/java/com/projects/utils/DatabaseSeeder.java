package com.projects.utils;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.projects.model.Ticket;
import com.projects.model.User;
import com.projects.repository.TicketRepository;
import com.projects.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;

    @Override
    public void run(String... args) throws Exception {
        List<User> users = userRepository.findAll();
        List<Ticket> tickets = ticketRepository.findAll();

        if (users.size() == 0 && tickets.size() == 0) {
            User dani = new User();
            dani.setLogin("dani");
            dani.setPassword("dani");

            User zsolt = new User();
            zsolt.setLogin("zsolt");
            zsolt.setPassword("zsolt");
            
            Ticket daniTicketFirst = new Ticket();
            daniTicketFirst.setTitle("Dani's 1st ticket");

            Ticket daniTicketSecond = new Ticket();
            daniTicketSecond.setTitle("Dani's 2nd ticket");

            Ticket daniTicketThird = new Ticket();
            daniTicketThird.setTitle("Dani's 3rd ticket");

            Ticket zsoltTicketFirst = new Ticket();
            zsoltTicketFirst.setTitle("Zsolt's 1st ticket");

            Ticket zsoltTicketSecond = new Ticket();
            zsoltTicketSecond.setTitle("Zsolt's 2nd ticket");

            // List<Ticket> daniTickets = ticketRepository.insert(List.of(daniTicketFirst, daniTicketSecond, daniTicketThird));
            // List<Ticket> zsoltTickets = ticketRepository.insert(List.of(zsoltTicketFirst, zsoltTicketSecond));

            // dani.setTickets(daniTickets);
            // zsolt.setTickets(zsoltTickets);

            daniTicketFirst.setUser(dani);
            daniTicketSecond.setUser(dani);
            daniTicketThird.setUser(dani);

            zsoltTicketFirst.setUser(zsolt);
            zsoltTicketSecond.setUser(zsolt);

            ticketRepository.insert(daniTicketFirst);
            ticketRepository.insert(daniTicketSecond);
            ticketRepository.insert(daniTicketThird);
            
            ticketRepository.insert(zsoltTicketFirst);
            ticketRepository.insert(zsoltTicketSecond);

            userRepository.insert(dani);
            userRepository.insert(zsolt);

            System.out.println("Inserted seed data!");
        } else {
            System.out.println("Not inserting seed data, database is not empty!");
        }
    }
}
