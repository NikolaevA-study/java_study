package org.example.saveuserinfoservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.example.saveuserinfoservice.dto.enums.HttpMethodDTO;

@Getter
@Setter
public class UserKafkaDTO {
    @NotBlank(message = "обязательное поле")
    private HttpMethodDTO httpMethod; // "POST", "PUT", "DELETE", "GET"
    @NotBlank(message = "обязательное поле")
    private UserDTO user;
}

