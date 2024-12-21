package edu.miu.ticket_system;

import edu.miu.ticket_system.entity.User;
import edu.miu.ticket_system.exception.UserNotFoundException;
import edu.miu.ticket_system.repository.UserRepository;
import edu.miu.ticket_system.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        userService.getAllUsers();
        verify(userRepository).findAll();
    }

    @Test
    void testSaveUser() {
        User user = new User();
        user.setUserName("john_doe");
        when(userRepository.save(any(User.class))).thenReturn(user);
        User savedUser = userService.saveUser(user);
        assertNotNull(savedUser);
        assertEquals("john_doe", savedUser.getUsername());
    }

    @Test
    void testGetUserById() throws UserNotFoundException {
        Integer userId = 1;
        User user = new User();
        user.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Optional<User> foundUser = userService.getUserById(userId);
        assertTrue(foundUser.isPresent());
        assertEquals(userId, foundUser.get().getId());
    }

    @Test
    void testGetUserByIdNotFound() {
        Integer userId = 1;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(userId));
    }
}

