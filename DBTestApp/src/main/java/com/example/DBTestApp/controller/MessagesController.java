package com.example.DBTestApp.controller;

import com.example.DBTestApp.model.Messages;
import com.example.DBTestApp.service.MessagesService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Component
@RestController // Аннотация, которая указывает, что это контроллер для обработки REST-запросов
@RequestMapping("/messages") // Все запросы к этому контроллеру будут начинаться с /messages
public class MessagesController {
    @Autowired // Внедрение зависимости (сервис)
    private MessagesService messagesService;

    // Обработчик GET-запроса для получения всех сообщенияй
    @GetMapping
    public List<Messages> getAllMessages() {
        return messagesService.getAllMessages(); // Вызываем метод из сервиса для получения всех сообщений
    }

    // Обработчик GET-запроса для получения сообщения по ID
    @GetMapping("/{id}")
    public Messages getMessageById(@PathVariable Long id) {
        return messagesService.getMessageById(id); // Вызываем метод из сервиса для получения сообщения по ID
    }

    // Обработчик POST-запроса для создания нового сообщения
    @PostMapping("/create")
    public Messages createMessage(@RequestBody Messages msg) {
        return messagesService.saveNewMessage(msg); // Вызываем метод из сервиса для сохранения нового сообщения
    }

    // Обработчик POST-запроса для обновления сообщения
    @PostMapping("/update")
    public Messages updateMessage(@RequestBody Messages msg) {
        return messagesService.updateMessageById(msg); // Вызываем метод из сервиса для обновления сообщения
    }

    // Обработчик POST-запроса для удаления сообщения
    @PostMapping("/delete")
    public List<Messages> deleteMessage(@RequestBody Messages msg) {
        return messagesService.deleteMessageById(msg.getId()); // Вызываем метод из сервиса для обновления сообщения
    }
    //deleteMessageById
}
