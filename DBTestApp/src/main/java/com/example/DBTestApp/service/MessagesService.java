package com.example.DBTestApp.service;

import com.example.DBTestApp.exception.MessageNotFoundException;
import com.example.DBTestApp.model.Messages;
import com.example.DBTestApp.repository.MessagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessagesService {

    private final MessagesRepository messagesRepository;

    // Метод для получения всех сообщений
    public List<Messages> getAllMessages() {
        return messagesRepository.findAll(); // findAll() — это метод JpaRepository для получения всех записей
    }

    // Метод для сохранения сообщения в базу данных
    public Messages saveNewMessage(Messages msg) {
        return messagesRepository.save(msg); // save() — сохраняет сообщения в базе данных
    }

    // Метод для обновления сообщения в базу данных
    public Messages updateMessageById(Messages msg) {
        Messages message = getMessageById(msg.getId());
        if (message == null) {
            throw new MessageNotFoundException();
        }
        return messagesRepository.save(msg);
    }

    // Метод для поиска сообщения по ID
    public Messages getMessageById(Long id) {
        Messages message = messagesRepository.findById(id).orElse(null);
        if (message == null) {
            throw new MessageNotFoundException();
        }
        return message; // findById() — ищет сообщение по ID
    }

    // Метод для удаления сообщения по ID
    public void deleteMessageById(Long id) {
        if(messagesRepository.checkIdIsExists(id) == null){
            throw new MessageNotFoundException();
        }
        messagesRepository.deleteById(id);
    }
}
