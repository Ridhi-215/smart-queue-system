package com.smartqueue.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int tokenNumber;

    private String status; // WAITING, SERVED, CANCELLED

    private LocalDateTime joinTime;

    // Relationship with Queue
    @ManyToOne
    @JoinColumn(name = "queue_id")
    private Queue queue;

    // Relationship with User
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}