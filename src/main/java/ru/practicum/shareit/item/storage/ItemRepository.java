package ru.practicum.shareit.item.storage;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.mapper.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.utility.InMemoryStorage;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ItemRepository extends InMemoryStorage<Item> {
    public List<ItemDto> searchByText(String text) {
        return storage.values().stream()
                .filter(item -> (item.getName().toLowerCase().contains(text.toLowerCase()) ||
                        item.getDescription().toLowerCase().contains(text.toLowerCase())) &&
                        item.getAvailable()
                )
                .map(ItemMapper::toItemDto)
                .collect(Collectors.toList());
    }
}
