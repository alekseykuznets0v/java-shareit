package ru.practicum.shareit.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.exceptions.ValidationException;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.service.UserService;
import ru.practicum.shareit.utility.validation.OnCreate;
import ru.practicum.shareit.utility.validation.OnUpdate;

import java.util.List;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@Slf4j
@RequestMapping(path = "/users")
@Validated
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDto create(@RequestBody @Validated(OnCreate.class) UserDto userDto, Errors errors) {
        log.info("Получен запрос POST /users/ " + userDto);
        if (errors.hasErrors()) {
            throw new ValidationException("Произошла ошибка валидации - " + errors.getAllErrors());
        } else {
            return userService.create(userDto);
        }
    }

    @GetMapping
    public List<UserDto> getAll() {
        log.info("Получен запрос GET /users");
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        log.info("Получен запрос GET /users/" + id);
        return userService.getUserById(id);
    }

    @PatchMapping("/{id}")
    public UserDto update(@RequestBody @Validated(OnUpdate.class) UserDto userDto,
                          @PathVariable long id, Errors errors) {
        log.info("Получен запрос PATCH /users/" + id + " с телом: " + userDto);
        if (errors.hasErrors()) {
            throw new ValidationException("Произошла ошибка валидации - " + errors.getAllErrors());
        } else {
            return userService.update(userDto, id);
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        log.info("Получен запрос DELETE /users/" + id);
        userService.deleteUserById(id);
        return "Пользователь с id=" + id + " удален";
    }
}
