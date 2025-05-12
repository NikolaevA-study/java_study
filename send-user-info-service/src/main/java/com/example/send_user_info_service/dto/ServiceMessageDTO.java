package com.example.send_user_info_service.dto;

import com.example.send_user_info_service.dto.enums.HttpMethodDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceMessageDTO {
    private HttpMethodDTO httpMethod; // "POST", "PUT", "DELETE", "GET"
    private UserDTO user;
}
