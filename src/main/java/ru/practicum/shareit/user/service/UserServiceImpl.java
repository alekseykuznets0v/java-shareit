package ru.practicum.shareit.user.service;

import org.springframework.stereotype.Service;
import ru.practicum.shareit.exceptions.AlreadyExistsException;
import ru.practicum.shareit.exceptions.NotFoundException;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.storage.UserStorage;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.practicum.shareit.user.mapper.UserMapper.*;

@Service
public class UserServiceImpl implements UserService {
    private final UserStorage userStorage;

    public UserServiceImpl(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    @Override
    public UserDto create(UserDto userDto) {
        String email = userDto.getEmail();
        if (userStorage.isEmailExist(email)) {
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
        Optional<User> user = userStorage.getUserById(id);
        if (user.isEmpty()) {
            throw new NotFoundException(String.format("Пользователь с id=%s не найден", id));
        }
        return toUserDto(user.get());
    }

    @Override
    public UserDto update(UserDto userDto, Long id) {
        Optional<User> user = userStorage.getUserById(id);
        if (user.isEmpty()) {
            throw new NotFoundException(String.format("Пользователь с id=%s не найден", id));
        }
        /*
        Optional<User> user = userStorage.getUserById(userId);

        if (user.isEmpty()) {
            throw new NotFoundException(String.format("Пользователь с id=%s не найден", userId));
        }

        Item oldItem = item.get();
        Long oldItemUserId = oldItem.getOwner().getId();

        if (!oldItemUserId.equals(userId)) {
            throw new NotFoundException(String.format("У пользователя с id=%s нет вещи с id=%s", userId, id));
        }

        Item itemToUpdate = oldItem.toBuilder()
                .name(itemDto.getName())
                .description(itemDto.getDescription())
                .available(itemDto.getAvailable())
                .build();
        return toItemDto(itemStorage.update(itemToUpdate));
        */
        return null;
    }

    @Override
    public void deleteUserById(Long id) {

    }
}
