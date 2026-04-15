package com.smartqueue.model;
import com.smartqueue.model.ServiceCenter;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Queue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // e.g. "Doctor OP", "Cash Counter"

    private int currentToken = 0;

    private String status; // OPEN, CLOSED

    @ManyToOne
    @JoinColumn(name = "service_center_id")
    private ServiceCenter serviceCenter;
}