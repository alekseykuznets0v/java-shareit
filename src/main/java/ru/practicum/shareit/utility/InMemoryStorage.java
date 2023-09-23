package ru.practicum.shareit.utility;

import java.util.*;

public abstract class InMemoryStorage<T extends Entity> {
    protected final Map<Long, T> storage = new HashMap<>();
    protected Long count = 0L;

    public T create(T t) {
        Long id = ++count;
        t.setId(id);
        storage.put(id, t);
        return t;
    }

    public Optional<T> getById(Long id) {
        T t = storage.get(id);
        return t == null ? Optional.empty() : Optional.of(t);
    }

    public List<T> getAll() {
        return new ArrayList<>(storage.values());
    }

    public T update(T t) {
        storage.put(t.getId(), t);
        return storage.get(t.getId());
    }

    public void deleteById(Long id) {
        storage.remove(id);
    }

    public boolean isIdExist(Long id) {
        return storage.containsKey(id);
    }
}
