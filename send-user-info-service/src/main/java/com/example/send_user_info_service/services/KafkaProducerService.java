package com.example.send_user_info_service.services;
import com.example.send_user_info_service.dto.UserKafkaMessage;
import com.example.send_user_info_service.model.UserModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate; // KafkaTemplate для отправки сообщений в Kafka

    // Метод отправки пользователя в Kafka
    public void sendToKafka(UserKafkaMessage userKafkaMessage) {
        try {
            // Отправляем сообщение в Kafka. Мы используем топик "user-topic".
            String msgJson = new ObjectMapper().writeValueAsString(userKafkaMessage);
            kafkaTemplate.send("user-topic", msgJson);

            // Логирование, чтобы видеть, что сообщение отправлено
            System.out.println("Message sent to Kafka: " + userKafkaMessage);
        } catch (Exception e) {
            // Обработка исключений, если что-то пошло не так
            e.printStackTrace();
        }
    }
}