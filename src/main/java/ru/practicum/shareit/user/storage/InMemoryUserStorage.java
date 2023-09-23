package ru.practicum.shareit.user.storage;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.utility.InMemoryStorage;

@Repository
public class InMemoryUserStorage extends InMemoryStorage<User> {
}
