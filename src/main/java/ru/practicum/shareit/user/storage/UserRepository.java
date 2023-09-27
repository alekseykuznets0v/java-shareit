package ru.practicum.shareit.user.storage;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.utility.InMemoryStorage;

import java.util.HashSet;
import java.util.Set;

@Repository
public class UserRepository extends InMemoryStorage<User> {
    private final Set<String> emails = new HashSet<>();

    public boolean saveEmailIfNotExists(String email) {
        return emails.add(email);
    }

    public void deleteEmail(String email) {
        emails.remove(email);
    }
}
