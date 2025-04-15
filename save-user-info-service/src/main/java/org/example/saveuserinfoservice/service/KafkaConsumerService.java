package org.example.saveuserinfoservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.saveuserinfoservice.dto.UserKafkaDTO;
import org.example.saveuserinfoservice.model.UserModel;
import org.example.saveuserinfoservice.model.UserContactsModel;
import org.example.saveuserinfoservice.dto.UserContactsDTO;
import org.example.saveuserinfoservice.dto.UserDTO;
import org.example.saveuserinfoservice.repository.UserContactsRepository;
import org.example.saveuserinfoservice.repository.UserRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final UserContactsRepository userContactsRepository;
    private final UserRepository userRepository;

    // Этот метод будет вызываться, когда приходит новое сообщение из Kafka
    @KafkaListener(topics = "user-topic", groupId = "user-info-consumer-group")
    @Transactional
    public void consume(String message) {
        try {
            // Десериализуем JSON в объект User
            ObjectMapper objectMapper = new ObjectMapper();
            UserKafkaDTO userKafkaDTO = objectMapper.readValue(message, UserKafkaDTO.class);
            String method = userKafkaDTO.getHttpMethod();
            UserDTO userDTO = userKafkaDTO.getUser();

            // Сохраняем пользователя в базе
            if(method.equals("POST")) {
                UserModel user = new UserModel();
                user.setName(userDTO.getName());
                user.setSurname(userDTO.getSurname());
                user.setAge(userDTO.getAge());
                user.setId(userDTO.getId());
                // Добавляем контакты
                for (UserContactsDTO userContactsDTO : userDTO.getContacts()) {
                    UserContactsModel userContacts = new UserContactsModel();
                    userContacts.setType(userContactsDTO.getType());
                    userContacts.setValue(userContactsDTO.getValue());
                    userContacts.setUser(user); // связываем контакт с пользователем
                    // Сохраняем контакт в базе
                    userContactsRepository.save(userContacts);
                }
            } else if(method.equals("DELETE")) {
                List<UserModel> users = userRepository.findUsersModelByNameAndSurname(userDTO.getName(), userDTO.getSurname());
                for (UserModel user : users) {
                    userContactsRepository.deleteUserContactsModelByUser(user);
                    userRepository.delete(user);
                }
            } else if(method.equals("PATCH")) {
                UserModel user = userRepository.findUserModelById(userDTO.getId());
                user.setName(userDTO.getName());
                user.setSurname(userDTO.getSurname());
                user.setAge(userDTO.getAge());
                userContactsRepository.deleteUserContactsModelByUser(user);
                for (UserContactsDTO userContactsDTO : userDTO.getContacts()) {
                    UserContactsModel userContacts = new UserContactsModel();
                    userContacts.setType(userContactsDTO.getType());
                    userContacts.setValue(userContactsDTO.getValue());
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
