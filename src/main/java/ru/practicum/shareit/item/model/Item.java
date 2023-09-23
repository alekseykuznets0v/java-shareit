package ru.practicum.shareit.item.model;

import lombok.*;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.utility.Entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * TODO Sprint add-controllers.
 */

@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
public class Item extends Entity {
    private User owner;
    @NotNull
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    private Boolean available;

    @Builder(toBuilder = true)
    public Item(Long id, User owner, String name, String description, Boolean available) {
        super(id);
        this.owner = owner;
        this.name = name;
        this.description = description;
        this.available = available;
    }

    @Override
    public String toString() {
        return "Item{" +
                "owner=" + owner +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", available=" + available +
                ", id=" + id +
                '}';
    }
}
