package com.example.send_user_info_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserContactsDTO {
    @NotBlank(message = "обязательное поле")
    private String type;
    @NotBlank(message = "обязательное поле")
    private String value;
}
