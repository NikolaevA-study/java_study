package com.example.send_user_info_service.controller;

import com.example.send_user_info_service.dto.UserKafkaMessage;
import com.example.send_user_info_service.model.UserModel;
import com.example.send_user_info_service.services.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

@RestController // Аннотация для Spring, чтобы сделать этот класс REST контроллером
@RequestMapping("/users") // Базовый путь для всех запросов в этом контроллере
@RequiredArgsConstructor
public class UserController {

    private final KafkaProducerService kafkaProducerService; // Сервис, отвечающий за отправку сообщений в Kafka

    @PostMapping
    public String createUser(@RequestBody UserModel user, HttpMethod httpMethod) {
        // Отправляем объект User в Kafka через сервис KafkaProducerService
        sendKafkaMessage(user, httpMethod);
        // Возвращаем сообщение об успешной отправке
        return "User Create sent to Kafka";
    }

    @DeleteMapping
    public String deleteUser(@RequestBody UserModel user, HttpMethod httpMethod) {
        sendKafkaMessage(user, httpMethod);
        return "User Delete sent to Kafka";
    }

    @PatchMapping
    public String updateUser(@RequestBody UserModel user, HttpMethod httpMethod) {
        sendKafkaMessage(user, httpMethod);
        return "User Delete sent to Kafka";
    }

    private void sendKafkaMessage(UserModel user, HttpMethod httpMethod) {
        // Отправляем объект User в Kafka через сервис KafkaProducerService
        UserKafkaMessage userKafkaMessage = new UserKafkaMessage();
        userKafkaMessage.setUser(user);
        userKafkaMessage.setHttpMethod(String.valueOf(httpMethod));
        kafkaProducerService.sendToKafka(userKafkaMessage);
    }
}