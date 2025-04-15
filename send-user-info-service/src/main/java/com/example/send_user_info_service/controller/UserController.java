package com.example.send_user_info_service.controller;

import com.example.send_user_info_service.dto.UserKafkaMessage;
import com.example.send_user_info_service.dto.UserDTO;
import com.example.send_user_info_service.services.KafkaProducerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController // Аннотация для Spring, чтобы сделать этот класс REST контроллером
@RequestMapping("/users") // Базовый путь для всех запросов в этом контроллере
@RequiredArgsConstructor
public class UserController {

    private final KafkaProducerService kafkaProducerService; // Сервис, отвечающий за отправку сообщений в Kafka

    @PostMapping
    public ResponseEntity<Map<String, String>> createUser(@Valid @RequestBody UserDTO user, HttpMethod httpMethod) {
        // Отправляем объект User в Kafka через сервис KafkaProducerService
        sendKafkaMessage(user, httpMethod);
        // Возвращаем сообщение об успешной отправке
        Map<String, String> response = new HashMap<>();
        response.put("result", "User Created sent to Kafka");
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping
    public ResponseEntity<Map<String, String>> deleteUser(@Valid @RequestBody UserDTO user, HttpMethod httpMethod) {
        sendKafkaMessage(user, httpMethod);
        Map<String, String> response = new HashMap<>();
        response.put("result", "User Delete sent to Kafka");
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping
    public ResponseEntity<Map<String, String>> updateUser(@Valid @RequestBody UserDTO user, HttpMethod httpMethod) {
        sendKafkaMessage(user, httpMethod);
        Map<String, String> response = new HashMap<>();
        response.put("result", "User Update sent to Kafka");
        return ResponseEntity.ok().body(response);
    }

    private void sendKafkaMessage(UserDTO user, HttpMethod httpMethod) {
        // Отправляем объект User в Kafka через сервис KafkaProducerService
        UserKafkaMessage userKafkaMessage = new UserKafkaMessage();
        userKafkaMessage.setUser(user);
        userKafkaMessage.setHttpMethod(String.valueOf(httpMethod));
        kafkaProducerService.sendToKafka(userKafkaMessage);
    }
}