package com.smartqueue.controller;

import com.smartqueue.model.Queue;
import com.smartqueue.model.User;
import com.smartqueue.repository.QueueRepository;
import com.smartqueue.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/init")
public class InitController {

    @Autowired
    private QueueRepository queueRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/setup")
    public String setup() {

        Queue q = new Queue();
        q.setName("Test Queue");
        q.setStatus("OPEN");
        q.setCurrentToken(0); // 🔥 IMPORTANT FIX
        queueRepository.save(q);

        User u = new User();
        u.setName("Ridhi");
        u.setEmail("ridhi@test.com");
        userRepository.save(u);

        return "Initialized!";
    }
}