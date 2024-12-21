package edu.miu.ticket_system.controller;

import edu.miu.ticket_system.entity.LeaseOfficeStaff;
import edu.miu.ticket_system.entity.MaintenanceStaff;
import edu.miu.ticket_system.entity.Resident;
import edu.miu.ticket_system.entity.User;
import edu.miu.ticket_system.repository.UserRepository;
import edu.miu.ticket_system.service.impl.UserService;
import edu.miu.ticket_system.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

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
    public ResponseEntity<Map<String, String>> login(@RequestBody User user) {
        Map<String, String> response = new HashMap<>();

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
            System.out.println("Authentication successful for user: " + user.getUsername());
            UserDetails userDetails = userService.loadUserByUsername(user.getUsername());

            String jwtToken = jwtUtil.generateToken(userDetails);
            String role = userDetails.getAuthorities().toString();

            System.out.println("Generated JWT token for user: " + user.getUsername());
            System.out.println("User role: " + role);

            response.put("token", jwtToken);
            response.put("role", role);
            response.put("status code", String.valueOf(HttpStatus.OK.value()));
            User tempUser = userRepository.findByUserName(user.getUsername()).get();
            response.put("firstName", tempUser.getFirstName());
            response.put("lastName", tempUser.getLastName());
            response.put("email", tempUser.getEmail());
            response.put("userRole", tempUser.getUserType().toString());
            response.put("id", tempUser.getId().toString());


            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            response.put("token", "");
            response.put("role", "");
            response.put("status code", String.valueOf(HttpStatus.UNAUTHORIZED.value()));

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

}