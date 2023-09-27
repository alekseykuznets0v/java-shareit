package ru.practicum.shareit.user.service;

import org.springframework.stereotype.Service;
import ru.practicum.shareit.exceptions.AlreadyExistsException;
import ru.practicum.shareit.exceptions.NotFoundException;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.storage.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.practicum.shareit.user.mapper.UserMapper.*;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userStorage;

    public UserServiceImpl(UserRepository userStorage) {
        this.userStorage = userStorage;
    }

    @Override
    public UserDto create(UserDto userDto) {
        String email = userDto.getEmail();
        if (!userStorage.saveEmailIfNotExists(email)) {
            throw new AlreadyExistsException(String.format("Пользователь с email=%s уже существует", email));
        }
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
        User userToUpdate = user.get();
        String newName = userDto.getName();
        String oldEmail = userToUpdate.getEmail();
        String newEmail = userDto.getEmail();

        if (newName != null) {
            userToUpdate.setName(newName);
        }
        if (newEmail != null && !newEmail.equals(oldEmail)) {
            if (!userStorage.saveEmailIfNotExists(newEmail)) {
                throw new AlreadyExistsException(String.format("Пользователь с email=%s уже существует", newEmail));
            }
            userStorage.deleteEmail(oldEmail);
            userToUpdate.setEmail(newEmail);
        }
        return toUserDto(userStorage.update(userToUpdate));
    }

    @Override
    public void deleteUserById(Long id) {
        if (!userStorage.isIdExist(id)) {
            throw new NotFoundException(String.format("Пользователь с id=%s не найден", id));
        }
        UserDto userDto = getUserById(id);
        userStorage.deleteEmail(userDto.getEmail());
        userStorage.deleteById(id);
    }
}
