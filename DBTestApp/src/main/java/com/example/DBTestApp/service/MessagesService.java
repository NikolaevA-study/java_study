package com.example.DBTestApp.service;

import com.example.DBTestApp.exception.MessageExistException;
import com.example.DBTestApp.exception.MessageNotFoundException;
import com.example.DBTestApp.model.Messages;
import com.example.DBTestApp.repository.MessagesRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MessagesService {

    @PersistenceContext
    private EntityManager em;
    @Autowired // Spring автоматически инжектирует зависимость (в данном случае репозиторий)
    private MessagesRepository messagesRepository;

    // Метод для получения всех сообщений
    public List<Messages> getAllMessages() {
        return messagesRepository.findAll(); // findAll() — это метод JpaRepository для получения всех записей
    }

    // Метод для сохранения сообщения в базу данных
    public Messages saveNewMessage(Messages msg) {
        Messages message = getMessageById(msg.getId());
        if(message != null) {
            throw new MessageExistException();
        } else {
            return messagesRepository.save(msg); // save() — сохраняет сообщения в базе данных
        }
    }

    // Метод для сохранения сообщения в базу данных
    public Messages updateMessageById(Messages msg) {
        Messages message = getMessageById(msg.getId());
        if(message != null) {
            message.setMessage(msg.getMessage());
            return messagesRepository.save(msg); // save() — сохраняет сообщения в базе данных
        } else {
            throw new MessageNotFoundException();
        }
    }

    // Метод для поиска сообщения по ID
    public Messages getMessageById(Long id) {
        Messages message = messagesRepository.findById(Math.toIntExact(id)).orElse(null);
        if(message != null) {
            return message; // findById() — ищет сообщение по ID
        } else {
            throw new MessageNotFoundException();
        }
    }

    // Метод для удаления сообщения по ID
    public List<Messages> deleteMessageById(Long id) {
        Messages message = getMessageById(id);
        if(message != null) {
            messagesRepository.deleteById(id.intValue());
            return messagesRepository.findAll();
        } else {
            throw new MessageNotFoundException();
        }
    }
}
