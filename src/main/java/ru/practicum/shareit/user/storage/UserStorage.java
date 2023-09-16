package ru.practicum.shareit.user.storage;

import ru.practicum.shareit.user.model.User;

import java.util.List;
import java.util.Optional;

public interface UserStorage {
    User create(User user);

    Optional<User> getUserById(Long id);

    List<User> getAll();

    User update(User user);

    void deleteUserById(Long id);

    boolean isUserIdExist(Long id);

    boolean isEmailExist(String email);
}
