package com.example.send_user_info_service.services;
import com.example.send_user_info_service.dto.ServiceMessageDTO;

public interface MessageSender {
    void send(ServiceMessageDTO message);
}
