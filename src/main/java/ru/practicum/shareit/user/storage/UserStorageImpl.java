package ru.practicum.shareit.user.storage;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.user.model.User;

import java.util.*;

@Repository
public class UserStorageImpl implements UserStorage {
    private final Map<Long, User> users = new HashMap<>();
    private Long count = 0L;

    @Override
    public User create(User user) {
        Long id = ++count;
        user.setId(id);
        users.put(id, user);
        return user;
    }

    @Override
    public Optional<User> getUserById(Long id) {
        User user = users.get(id);
        return user == null ? Optional.empty() : Optional.of(user);
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public User update(User user) {
        users.put(user.getId(), user);
        return users.get(user.getId());
    }

    @Override
    public void deleteUserById(Long id) {
        users.remove(id);
    }

    @Override
    public boolean isUserIdExist(Long id) {
        return users.containsKey(id);
    }

    @Override
    public boolean isEmailExist(String email) {
        Optional<User> optionalUser = users.values().stream().filter(user -> user.getEmail().equals(email)).findFirst();
        return optionalUser.isPresent();
    }
}
