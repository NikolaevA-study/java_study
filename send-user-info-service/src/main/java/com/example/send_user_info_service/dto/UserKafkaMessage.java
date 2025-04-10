package com.example.send_user_info_service.dto;

import com.example.send_user_info_service.model.UserModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserKafkaMessage  {
    private String httpMethod; // "POST", "PUT", "DELETE", "GET"
    private UserModel user;
}
