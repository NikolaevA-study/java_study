package com.example.send_user_info_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceMessageDTO {
    private String httpMethod; // "POST", "PUT", "DELETE", "GET"
    private UserDTO user;
}
