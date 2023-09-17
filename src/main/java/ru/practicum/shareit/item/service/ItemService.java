package ru.practicum.shareit.item.service;

import ru.practicum.shareit.item.dto.ItemDto;

import java.util.List;

public interface ItemService {
    ItemDto create(ItemDto itemDto, Long userId);

    ItemDto getItemById(Long id);

    List<ItemDto> getAllByUserId(Long userId);

    ItemDto update(ItemDto itemDto, Long id, Long userId);

    void deleteItemById(Long id);

    List<ItemDto> searchByText(String text);
}
