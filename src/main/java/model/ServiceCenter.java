package com.smartqueue.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ServiceCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String location;

    private String type; // BANK, HOSPITAL, SALON
}