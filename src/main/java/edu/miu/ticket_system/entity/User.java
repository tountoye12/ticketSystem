package edu.miu.ticket_system.entity;

import jakarta.persistence.*;

@Entity
public class User {
    @Id
    private Integer Id;
    private String firstName;

}
