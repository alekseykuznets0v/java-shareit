package ru.practicum.shareit.utility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public abstract class Entity {
    protected Long id;
}
