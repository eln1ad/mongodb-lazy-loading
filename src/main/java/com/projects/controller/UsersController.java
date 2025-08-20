package com.projects.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projects.model.Ticket;
import com.projects.model.User;
import com.projects.service.TicketService;
import com.projects.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UsersController {
    private final UserService userService;
    private final TicketService ticketService;

    @GetMapping
    public List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) throws Exception {
        return userService.findById(id);
    }

    @PostMapping
    public User postUser(@RequestBody User user) {
        return userService.create(user);
    }

    @PostMapping("/{id}/tickets")
    public void postTicketAsUser(@PathVariable String id, @RequestBody Ticket ticket) throws Exception {
        ticketService.addTicketToUser(id, ticket);
    }

    @PutMapping
    public void putUserById(@PathVariable String id, @RequestBody User user) throws Exception {
        userService.updateById(id, user);
    }
}
