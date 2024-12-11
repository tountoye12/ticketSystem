package edu.miu.ticket_system.repository;

import edu.miu.ticket_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
