package org.example.saveuserinfoservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.saveuserinfoservice.dto.UserKafkaDTO;
import org.example.saveuserinfoservice.mapper.UserMapper;
import org.example.saveuserinfoservice.model.UserModel;
import org.example.saveuserinfoservice.dto.UserDTO;
import org.example.saveuserinfoservice.repository.UserContactsRepository;
import org.example.saveuserinfoservice.repository.UserRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final UserContactsRepository userContactsRepository;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;
    private final UserMapper userMapper;

    // Этот метод будет вызываться, когда приходит новое сообщение из Kafka
    @KafkaListener(topics = "user-topic", groupId = "user-info-consumer-group")
    @Transactional
    public void consume(String message) {
        try {
            // Десериализуем JSON в объект User
            UserKafkaDTO userKafkaDTO = objectMapper.readValue(message, UserKafkaDTO.class);
            String method = userKafkaDTO.getHttpMethod().toValue();
            UserDTO userDTO = userKafkaDTO.getUser();
            // Сохраняем пользователя в базе
            switch (method) {
                case "POST" -> invokePost(userDTO);
                case "DELETE" -> invokeDelete(userDTO);
                case "PATCH" -> invokePatch(userDTO);
            }
        } catch (Exception e) {
            // Логируем ошибку, если не удается обработать сообщение
            e.printStackTrace();
        }
    }

    private void invokePost(UserDTO userDTO) {
        userRepository.save(userMapper.toEntity(userDTO));
    }

    private void invokeDelete(UserDTO userDTO) {
        List<UserModel> users = userRepository.findByNameAndSurname(userDTO.getName(), userDTO.getSurname());
        userRepository.deleteAll(users);
    }

    private void invokePatch(UserDTO userDTO) {
        Optional<UserModel> userOptional = userRepository.findById(userDTO.getId());
        if (userOptional.isPresent()) {
            userContactsRepository.deleteAll(userContactsRepository.findByUser(userOptional.get()));
            userRepository.save(userMapper.toEntity(userDTO));
        }
    }
}
