package ru.practicum.shareit.user.service;

import org.springframework.stereotype.Service;
import ru.practicum.shareit.exceptions.NotFoundException;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.storage.InMemoryUserStorage;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.practicum.shareit.user.mapper.UserMapper.*;

@Service
public class UserServiceImpl implements UserService {
    private final InMemoryUserStorage userStorage;

    public UserServiceImpl(InMemoryUserStorage userStorage) {
        this.userStorage = userStorage;
    }

    @Override
    public UserDto create(UserDto userDto) {
        User user = toUser(userDto);
        User savedUser = userStorage.create(user);
        return toUserDto(savedUser);
    }

    @Override
    public List<UserDto> getAll() {
        return userStorage.getAll().stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<User> user = userStorage.getById(id);
        if (user.isEmpty()) {
            throw new NotFoundException(String.format("Пользователь с id=%s не найден", id));
        }
        return toUserDto(user.get());
    }

    @Override
    public UserDto update(UserDto userDto, Long id) {
        Optional<User> user = userStorage.getById(id);
        if (user.isEmpty()) {
            throw new NotFoundException(String.format("Пользователь с id=%s не найден", id));
        }
        User oldUser = user.get();
        User userToUpdate = oldUser.toBuilder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .build();
        return toUserDto(userStorage.update(userToUpdate));
    }

    @Override
    public void deleteUserById(Long id) {
        if (!userStorage.isIdExist(id)) {
            throw new NotFoundException(String.format("Пользователь с id=%s не найден", id));
        }
        userStorage.deleteById(id);
    }
}
