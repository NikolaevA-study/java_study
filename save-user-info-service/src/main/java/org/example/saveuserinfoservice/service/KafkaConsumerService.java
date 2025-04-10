package org.example.saveuserinfoservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.example.saveuserinfoservice.dto.UserKafkaMessage;
import org.example.saveuserinfoservice.model.UserModel;
import org.example.saveuserinfoservice.model.UserContactsModel;
import org.example.saveuserinfoservice.message.UserContactsMessage;
import org.example.saveuserinfoservice.message.UserMessage;
import org.example.saveuserinfoservice.repository.UserContactsRepository;
import org.example.saveuserinfoservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KafkaConsumerService {

    @Autowired
    private UserContactsRepository userContactsRepository;
    @Autowired
    private UserRepository userRepository;

    // Этот метод будет вызываться, когда приходит новое сообщение из Kafka
    @KafkaListener(topics = "user-topic", groupId = "user-info-consumer-group")
    @Transactional
    public void consume(String message) {
        try {
            // Десериализуем JSON в объект User
            ObjectMapper objectMapper = new ObjectMapper();
            UserKafkaMessage userKafkaMessage = objectMapper.readValue(message, UserKafkaMessage.class);
            String method = userKafkaMessage.getHttpMethod();
            UserMessage userMessage = userKafkaMessage.getUser();

            // Сохраняем пользователя в базе
            if(method.equals("POST")) {
                UserModel user = new UserModel();
                user.setName(userMessage.getName());
                user.setSurname(userMessage.getSurname());
                user.setAge(userMessage.getAge());
                user.setId(userMessage.getId());
                // Добавляем контакты
                for (UserContactsMessage userContactsMessage : userMessage.getContacts()) {
                    UserContactsModel userContacts = new UserContactsModel();
                    userContacts.setType(userContactsMessage.getType());
                    userContacts.setValue(userContactsMessage.getValue());
                    userContacts.setUser(user); // связываем контакт с пользователем
                    // Сохраняем контакт в базе
                    userContactsRepository.save(userContacts);
                }
            } else if(method.equals("DELETE")) {
                List<UserModel> users = userRepository.findUsersModelByNameAndSurname(userMessage.getName(),userMessage.getSurname());
                for (UserModel user : users) {
                    userContactsRepository.deleteUserContactsModelByUser(user);
                    userRepository.delete(user);
                }
            } else if(method.equals("PATCH")) {
                UserModel user = userRepository.findUserModelById(userMessage.getId());
                user.setName(userMessage.getName());
                user.setSurname(userMessage.getSurname());
                user.setAge(userMessage.getAge());
                userContactsRepository.deleteUserContactsModelByUser(user);
                for (UserContactsMessage userContactsMessage : userMessage.getContacts()) {
                    UserContactsModel userContacts = new UserContactsModel();
                    userContacts.setType(userContactsMessage.getType());
                    userContacts.setValue(userContactsMessage.getValue());
                    userContacts.setUser(user); // связываем контакт с пользователем
                    // Сохраняем контакт в базе
                    userContactsRepository.save(userContacts);
                }
                userRepository.save(user);
            }
    } catch (Exception e) {
            // Логируем ошибку, если не удается обработать сообщение
            e.printStackTrace();
        }
    }
}
