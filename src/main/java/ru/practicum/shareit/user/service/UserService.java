package ru.practicum.shareit.user.service;

import ru.practicum.shareit.user.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto create(UserDto userDto);

    List<UserDto> getAll();

    UserDto getUserById(Long id);

    UserDto update(UserDto userDto, Long id);

    void deleteUserById(Long id);
}
