package ru.practicum.shareit.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.utility.validation.OnCreate;
import ru.practicum.shareit.utility.validation.OnUpdate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private Long id;
    @NotBlank(groups = OnCreate.class)
    private String name;
    @NotBlank(groups = OnCreate.class)
    @Email(groups = {OnCreate.class, OnUpdate.class})
    private String email;
}
