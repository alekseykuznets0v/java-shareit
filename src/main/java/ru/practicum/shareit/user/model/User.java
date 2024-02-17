package ru.practicum.shareit.user.model;

import lombok.*;
import ru.practicum.shareit.utility.Entity;

@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
public class User extends Entity {
    private String name;
    private String email;

    @Builder(toBuilder = true)
    public User(Long id, String name, String email) {
        super(id);
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                '}';
    }
}
