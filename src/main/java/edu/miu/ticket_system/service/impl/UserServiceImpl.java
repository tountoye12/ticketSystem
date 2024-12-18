package edu.miu.ticket_system.service.impl;


import edu.miu.ticket_system.entity.User;
import edu.miu.ticket_system.enums.UserType;
import edu.miu.ticket_system.repository.UserRepository;
import edu.miu.ticket_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findUserById(id);
    }

    @Override
    public List<User> getUsersByType(UserType userType) {
        return userRepository.findUsersByUserType(userType);
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
