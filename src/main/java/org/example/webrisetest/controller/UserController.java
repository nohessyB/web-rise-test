package org.example.webrisetest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.webrisetest.dto.UserDto;
import org.example.webrisetest.entity.User;
import org.example.webrisetest.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User Management", description = "API для управления пользователями")
public class UserController {
    private final UserService userService;

    @Operation(
            summary = "Получить всех пользователей",
            description = "Возвращает данные пользователей"
    )
    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @Operation(
            summary = "Получить пользователя по ID",
            description = "Возвращает данные пользователя по его идентификатору"
    )
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @Operation(
            summary = "Создать нового пользователя",
            description = "Создает нового пользователя на основе переданных данных"
    )
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.save(userDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Обновить данные пользователя",
            description = "Обновляет данные существующего пользователя"
    )
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,
                                           @RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.update(id, userDto), HttpStatus.OK);
    }

    @Operation(
            summary = "Удалить пользователя",
            description = "Удаляет пользователя по указанному идентификатору"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(String.format("User with id %s was deleted", id), HttpStatus.OK);
    }
}
