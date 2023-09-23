package ru.practicum.shareit.user.model;

import lombok.*;
import ru.practicum.shareit.utility.Entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * TODO Sprint add-controllers.
 */

@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
public class User extends Entity {
    @NotBlank
    private String name;
    @NotBlank
    @Email
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
