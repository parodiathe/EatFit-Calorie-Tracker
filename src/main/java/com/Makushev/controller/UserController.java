package com.Makushev.controller;

import com.Makushev.exception.UserException;
import com.Makushev.model.User;
import com.Makushev.services.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@OpenAPIDefinition(info = @Info(title = "REST API service for tracking the user's daily calorie intake and accounting for meals eaten", version = "1.2", description = "Test Project for 1221Systems"))
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping
    @Operation(summary = "Создать нового пользователя", description = "Создаем нового пользователя")
    public ResponseEntity<User> createUser(@RequestBody @Valid User user){
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Получить список всех пользователей", description = "Получаем список всех пользователей")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить пользователя по id", description = "Получаем инофрмацию о пользователе по его id")
    public ResponseEntity<User> getUserById(@PathVariable Long id) throws UserException {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить информацию пользователя", description = "Обновить информацию пользователя")
    public ResponseEntity<User> updateUser(@RequestBody User user,
                                           @PathVariable Long id) throws UserException {
        User updatedUser = userService.updateUser(id, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить пользователя", description = "Удаляем пользователя")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) throws Exception{

        userService.deleteUser(id);
        return new ResponseEntity<>("User deleted", HttpStatus.ACCEPTED);
    }
}
