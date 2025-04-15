package com.example.send_user_info_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserContactsDTO {
    @NotBlank(message = "не может быть пустым")
    @NotNull(message = "обязательное поле")
    private String type;
    @NotBlank(message = "не может быть пустым")
    @NotNull(message = "обязательное поле")
    private String value;
}
