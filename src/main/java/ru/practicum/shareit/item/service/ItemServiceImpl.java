package ru.practicum.shareit.item.service;

import org.springframework.stereotype.Service;
import ru.practicum.shareit.exceptions.NotFoundException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.mapper.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.storage.InMemoryItemStorage;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.storage.InMemoryUserStorage;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.practicum.shareit.item.mapper.ItemMapper.*;

@Service
public class ItemServiceImpl implements ItemService {
    private final InMemoryItemStorage itemStorage;
    private final InMemoryUserStorage userStorage;

    public ItemServiceImpl(InMemoryItemStorage itemStorage, InMemoryUserStorage userStorage) {
        this.itemStorage = itemStorage;
        this.userStorage = userStorage;
    }

    @Override
    public ItemDto create(ItemDto itemDto, Long userId) {
        Optional<User> user = userStorage.getById(userId);
        if (user.isEmpty()) {
            throw new NotFoundException(String.format("Пользователь с id=%s не найден", userId));
        }
        Item item = toItem(itemDto);
        item.setOwner(user.get());
        Item savedItem = itemStorage.create(item);
        return toItemDto(savedItem);
    }

    @Override
    public ItemDto getItemById(Long id) {
        Optional<Item> item = itemStorage.getById(id);
        if (item.isEmpty()) {
            throw new NotFoundException(String.format("Вещь с id=%s не найдена", id));
        }
        return toItemDto(item.get());
    }

    @Override
    public List<ItemDto> getAllByUserId(Long userId) {
        return itemStorage.getAll().stream()
                .filter(item -> item.getOwner().getId().equals(userId))
                .map(ItemMapper::toItemDto)
                .collect(Collectors.toList());
    }

    @Override
    public ItemDto update(ItemDto itemDto, Long id, Long userId) {
       Optional<Item> item = itemStorage.getById(id);
       if (item.isEmpty()) {
           throw new NotFoundException(String.format("Вещь с id=%s не найдена", id));
       }
       Optional<User> user = userStorage.getById(userId);
       if (user.isEmpty()) {
           throw new NotFoundException(String.format("Пользователь с id=%s не найден", userId));
       }

       Item oldItem = item.get();
       Long oldItemUserId = oldItem.getOwner().getId();

       if (!oldItemUserId.equals(userId)) {
           throw new NotFoundException(String.format("У пользователя с id=%s нет вещи с id=%s", userId, id));
       }

       Item itemToUpdate = oldItem.toBuilder()
               .name(itemDto.getName())
               .description(itemDto.getDescription())
               .available(itemDto.getAvailable())
               .build();
       return toItemDto(itemStorage.update(itemToUpdate));
    }

    @Override
    public void deleteItemById(Long id) {
        if (!itemStorage.isIdExist(id)) {
            throw new NotFoundException(String.format("Вещь с id=%s не найдена", id));
        }
        itemStorage.deleteById(id);
    }

    @Override
    public List<ItemDto> searchByText(String text) {
        if (text.isBlank()) return Collections.emptyList();
        return itemStorage.getAll().stream()
                .filter(item -> (item.getName().toLowerCase().contains(text.toLowerCase()) ||
                                item.getDescription().toLowerCase().contains(text.toLowerCase())) &&
                                item.getAvailable()
                )
                .map(ItemMapper::toItemDto)
                .collect(Collectors.toList());
    }
}
