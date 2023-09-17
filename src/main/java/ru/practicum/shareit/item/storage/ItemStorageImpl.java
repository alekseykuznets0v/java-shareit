package ru.practicum.shareit.item.storage;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.model.Item;

import java.util.*;

@Repository
public class ItemStorageImpl implements ItemStorage {
    private final Map<Long, Item> items = new HashMap<>();
    private Long count = 0L;

    @Override
    public Item create(Item item) {
        Long id = ++count;
        item.setId(id);
        items.put(id, item);
        return item;
    }

    @Override
    public Optional<Item> getItemById(Long id) {
        Item item = items.get(id);
        return item == null ? Optional.empty() : Optional.of(item);
    }

    @Override
    public List<Item> getAll() {
        return new ArrayList<>(items.values());
    }

    @Override
    public Item update(Item item) {
        items.put(item.getId(), item);
        return items.get(item.getId());
    }

    @Override
    public void deleteItemById(Long id) {
        items.remove(id);
    }

    @Override
    public boolean isItemIdExist(Long id) {
        return items.containsKey(id);
    }
}
