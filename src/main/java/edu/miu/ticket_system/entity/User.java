package edu.miu.ticket_system.entity;

import edu.miu.ticket_system.enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private String contactNumber;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
