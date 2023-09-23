package ru.practicum.shareit.item.storage;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.utility.InMemoryStorage;

@Repository
public class InMemoryItemStorage extends InMemoryStorage<Item> {
}
