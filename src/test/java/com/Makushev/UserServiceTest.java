package com.Makushev;

import com.Makushev.domain.Gender;
import com.Makushev.exception.UserException;
import com.Makushev.model.User;
import com.Makushev.repository.UserRepository;
import com.Makushev.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        User user = new User();
        user.setFullName("Makushev Daniil");
        user.setEmail("parodiathe@mail.ru");
        user.setAge(20);
        user.setWeight(70);
        user.setHeight(185);
        user.setGender(Gender.MALE);

        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User savedUser = invocation.getArgument(0);
            savedUser.setId(1L);
            return savedUser;
        });

        User createdUser = userService.createUser(user);

        assertNotNull(createdUser, "Созданный пользователь не должен быть null");
        assertEquals("Makushev Daniil", createdUser.getFullName(), "Имя пользователя должно совпадать");
        assertNotNull(createdUser.getDailyCalories(), "Ежедневная норма калорий должна быть рассчитана");

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testGetUserById() throws UserException {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setFullName("Makushev Daniil");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User foundUser = userService.getUserById(userId);

        assertNotNull(foundUser, "Найденный пользователь не должен быть null");
        assertEquals(userId, foundUser.getId(), "ID пользователя должно совпадать");
        assertEquals("Makushev Daniil", foundUser.getFullName(), "Имя пользователя должно совпадать");

        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testGetAllUsers() {
        User user1 = new User();
        user1.setId(1L);
        user1.setFullName("Makushev Daniil");

        User user2 = new User();
        user2.setId(2L);
        user2.setFullName("Vyborg Dmitriy");

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<User> users = userService.getAllUsers();

        assertNotNull(users, "Список пользователей не должен быть null");
        assertEquals(2, users.size(), "Должны быть возвращены два пользователя");
        assertEquals("Makushev Daniil", users.get(0).getFullName(), "Первый пользователь должен быть Makushev Daniil");
        assertEquals("Vyborg Dmitriy", users.get(1).getFullName(), "Второй пользователь должен быть Vyborg Dmitriy");

        verify(userRepository, times(1)).findAll();
    }
}