package edu.miu.ticket_system.service;

import edu.miu.ticket_system.entity.Ticket;
import edu.miu.ticket_system.entity.User;
import edu.miu.ticket_system.enums.Priority;
import edu.miu.ticket_system.enums.UserType;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User saveUser(User user);

    User getUserById(Integer id);

    List<User> getUsersByType(UserType userType);

    void delete(Integer id);
}
