package edu.miu.ticket_system.service;

import edu.miu.ticket_system.entity.Ticket;
import edu.miu.ticket_system.entity.User;
import edu.miu.ticket_system.enums.Priority;
import edu.miu.ticket_system.enums.UserType;
import edu.miu.ticket_system.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    List<User> getAllUsers();

    User saveUser(User user);

    Optional<User> getUserById(Integer id) throws UserNotFoundException;

    List<User> getUsersByType(UserType userType);

    void delete(Integer id);

    User getUserByUserName(String username);
}
