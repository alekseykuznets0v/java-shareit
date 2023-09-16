package ru.practicum.shareit.item.storage;

import ru.practicum.shareit.item.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemStorage {
    Item create(Item item);

    Optional<Item> getItemById(Long id);

    List<Item> getAll();

    Item update(Item item);

    void deleteItemById(Long id);

    boolean isItemIdExist(Long id);
}
