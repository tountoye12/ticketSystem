package edu.miu.ticket_system.entity;

import edu.miu.ticket_system.enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private String contactNumber;
    @Enumerated(EnumType.STRING)
    private UserType userType;

//    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

//    @Column(nullable = false)
    private LocalDateTime updatedAt;

   @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (userType != null) {
            return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + userType.name()));
        }
        return Collections.emptyList();
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void populateFromUser(User user) {
       this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.userName = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.contactNumber = user.getContactNumber();
        this.userType = user.getUserType();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
