package com.example.send_user_info_service.controller;

import com.example.send_user_info_service.dto.ServiceMessageDTO;
import com.example.send_user_info_service.dto.UserDTO;
import com.example.send_user_info_service.dto.enums.HttpMethodDTO;
import com.example.send_user_info_service.services.MessageSender;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController // Аннотация для Spring, чтобы сделать этот класс REST контроллером
@RequestMapping("/users") // Базовый путь для всех запросов в этом контроллере
@RequiredArgsConstructor
public class UserController {
    @Value("${app.messaging}")
    private String messagerName;
    private final MessageSender messageSender;

    @PostMapping
    public ResponseEntity<Map<String, String>> createUser(@Valid @RequestBody UserDTO user, HttpMethod httpMethod) {
        // Отправляем объект User в Kafka через сервис KafkaProducerService
        sendMessage(user, httpMethod);
        // Возвращаем сообщение об успешной отправке
        return ResponseEntity.ok().body(getResponse(httpMethod));
    }

    @DeleteMapping
    public ResponseEntity<Map<String, String>> deleteUser(@Valid @RequestBody UserDTO user, HttpMethod httpMethod) {
        sendMessage(user, httpMethod);
        return ResponseEntity.ok().body(getResponse(httpMethod));
    }

    @PatchMapping
    public ResponseEntity<Map<String, String>> updateUser(@Valid @RequestBody UserDTO user, HttpMethod httpMethod) {
        sendMessage(user, httpMethod);
        return ResponseEntity.ok().body(getResponse(httpMethod));
    }

    private Map<String,String> getResponse(HttpMethod httpMethod) {
        return Map.of("result", "Message send to " + messagerName + " method: " + httpMethod.name());
    }

    private void sendMessage(UserDTO user, HttpMethod httpMethod) {
        ServiceMessageDTO serviceMessageDTO = new ServiceMessageDTO();
        serviceMessageDTO.setUser(user);
        serviceMessageDTO.setHttpMethod(HttpMethodDTO.fromValue(httpMethod.name()));
        messageSender.send(serviceMessageDTO);
    }
}