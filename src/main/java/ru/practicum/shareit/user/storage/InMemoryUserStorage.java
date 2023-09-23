package ru.practicum.shareit.user.storage;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.exceptions.AlreadyExistsException;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.utility.InMemoryStorage;
import java.util.Optional;

@Repository
public class InMemoryUserStorage extends InMemoryStorage<User> {
    @Override
    public User create(User user) {
        String email = user.getEmail();
        if (isEmailExist(email)) {
            throw new AlreadyExistsException(String.format("Пользователь с email=%s уже существует", email));
        }
        return super.create(user);
    }

    private boolean isEmailExist(String email) {
        Optional<User> optionalUser = storage.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
        return optionalUser.isPresent();
    }
}
