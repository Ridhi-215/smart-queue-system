package com.smartqueue.controller;

import com.smartqueue.model.*;
import com.smartqueue.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/init")
public class InitController {

    @Autowired
    private ServiceCenterRepository serviceCenterRepository;

    @Autowired
    private QueueRepository queueRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/setup")
    public String setup() {

        ServiceCenter sc = new ServiceCenter();
        sc.setName("HDFC Bank");
        sc.setLocation("Mumbai");
        sc.setType("BANK");
        serviceCenterRepository.save(sc);

        Queue q = new Queue();
        q.setName("Withdrawal");
        q.setStatus("OPEN");
        q.setServiceCenter(sc);
        queueRepository.save(q);

        User u = new User();
        u.setName("Ridhi");
        u.setEmail("ridhi@test.com");
        userRepository.save(u);

        return "Initialized!";
    }
}