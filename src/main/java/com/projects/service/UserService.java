package com.projects.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projects.model.User;
import com.projects.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) throws Exception {
        return userRepository.findById(id).orElseThrow(() -> new Exception());
    }

    public void updateById(String id, User user) throws Exception {
        User oldUser = userRepository.findById(id).orElseThrow(() -> new Exception());
        
        if (user.getLogin() != null && user.getLogin() != "") {
            oldUser.setLogin(user.getLogin());
        }
        if (user.getPassword() != null && user.getPassword() != "") {
            oldUser.setPassword(user.getPassword());
        }
        if (user.getTickets() != null) {
            oldUser.setTickets(user.getTickets());
        }

        userRepository.save(oldUser);
    }

    public User create(User user) {
        return userRepository.insert(user);
    }
}
