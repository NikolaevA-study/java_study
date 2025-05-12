package com.example.send_user_info_service.dto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class UserDTO {
    @NotNull(message = "обязательное поле")
    private Long id;
    @NotBlank(message = "обязательное поле")
    private String name;
    private String surname;
    @NotNull(message = "обязательное поле")
    private Integer age;
    @Valid
    private List<UserContactsDTO> contacts;
}
