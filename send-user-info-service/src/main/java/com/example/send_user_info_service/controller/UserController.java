package com.example.send_user_info_service.controller;

import com.example.send_user_info_service.dto.ServiceMessageDTO;
import com.example.send_user_info_service.dto.UserDTO;
import com.example.send_user_info_service.services.MessageSender;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
        Map<String, String> response = new HashMap<>();
        response.put("result", "User Created sent to " + messagerName);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping
    public ResponseEntity<Map<String, String>> deleteUser(@Valid @RequestBody UserDTO user, HttpMethod httpMethod) {
        sendMessage(user, httpMethod);
        Map<String, String> response = new HashMap<>();
        response.put("result", "User Delete sent to " + messagerName);
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping
    public ResponseEntity<Map<String, String>> updateUser(@Valid @RequestBody UserDTO user, HttpMethod httpMethod) {
        sendMessage(user, httpMethod);
        Map<String, String> response = new HashMap<>();
        response.put("result", "User Update sent to " + messagerName);
        return ResponseEntity.ok().body(response);
    }

    private void sendMessage(UserDTO user, HttpMethod httpMethod) {
        ServiceMessageDTO serviceMessageDTO = new ServiceMessageDTO();
        serviceMessageDTO.setUser(user);
        serviceMessageDTO.setHttpMethod(String.valueOf(httpMethod));
        messageSender.send(serviceMessageDTO);
    }
}