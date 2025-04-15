package org.example.saveuserinfoservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserKafkaDTO {
    private String httpMethod; // "POST", "PUT", "DELETE", "GET"
    private UserDTO user;
}

