package edu.miu.ticket_system.repository;

import edu.miu.ticket_system.entity.User;
import edu.miu.ticket_system.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByUserType(UserType userType);
}
