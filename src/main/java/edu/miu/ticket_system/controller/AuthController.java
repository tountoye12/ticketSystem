package edu.miu.ticket_system.controller;

import edu.miu.ticket_system.entity.LeaseOfficeStaff;
import edu.miu.ticket_system.entity.MaintenanceStaff;
import edu.miu.ticket_system.entity.Resident;
import edu.miu.ticket_system.entity.User;
import edu.miu.ticket_system.service.impl.UserService;
import edu.miu.ticket_system.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    AuthController(AuthenticationManager authenticationManager, UserService userService, JwtUtil jwtUtil,
                   PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;

    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User newUser = new User();
        switch (user.getUserType()) {
            case MAINTENANCE_STAFF -> {
                newUser = new MaintenanceStaff();
            }
            case RESIDENT -> newUser = new Resident();
            case LEASE_OFFICE_STAFF -> newUser = new LeaseOfficeStaff();
            default -> throw new IllegalArgumentException("Unsupported user type: " + user.getUserType());
        }

        newUser.populateFromUser(user);
        userService.saveUser(newUser);
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        try {
            // Log incoming request
            System.out.println("Attempting to authenticate user: " + user.getUsername());

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
            System.out.println("Authentication successful for user: " + user.getUsername());

            // Load user details and generate token
            UserDetails userDetails = userService.loadUserByUsername(user.getUsername());
            String jwtToken = jwtUtil.generateToken(userDetails);

            System.out.println("Generated JWT token for user: " + user.getUsername());
            return ResponseEntity.ok(jwtToken);
        } catch (BadCredentialsException e) {
            // Log exception
            System.err.println("Authentication failed for user: " + user.getUsername());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }


}
