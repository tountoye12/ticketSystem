package edu.miu.ticket_system.service.impl;


import edu.miu.ticket_system.entity.User;
import edu.miu.ticket_system.enums.UserType;
import edu.miu.ticket_system.exception.UserNotFoundException;
import edu.miu.ticket_system.repository.UserRepository;
import edu.miu.ticket_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Integer id) throws UserNotFoundException {
        return Optional.ofNullable(userRepository
                .findById(id)
                .orElseThrow(
                        () -> new UserNotFoundException("No user with id:  + id")
                ));
    }

    @Override
    public List<User> getUsersByType(UserType userType) {
        return null;
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserByUserName(String username) {
        return userRepository.findByUserName(username).orElse(null);
    }
}
