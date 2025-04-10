package org.example.saveuserinfoservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.saveuserinfoservice.message.UserMessage;

@Getter
@Setter
public class UserKafkaMessage  {
    private String httpMethod; // "POST", "PUT", "DELETE", "GET"
    private UserMessage user;
}

