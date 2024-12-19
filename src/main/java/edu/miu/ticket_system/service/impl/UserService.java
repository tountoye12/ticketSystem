package edu.miu.ticket_system.service.impl;

import edu.miu.ticket_system.entity.User;
import edu.miu.ticket_system.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(User user) {
//        System.out.println("password: " + user.getPassword());
//        String encrypted = passwordEncoder.encode(user.getPassword());
////        user.setPassword(encrypted);
//        if (!user.getPassword().startsWith("$2a$")) { // Check if it's not already hashed
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//        }
//        System.out.println("encrypted: " + encrypted);
        userRepository.save(user);
    }


    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + userName));
    }

}
