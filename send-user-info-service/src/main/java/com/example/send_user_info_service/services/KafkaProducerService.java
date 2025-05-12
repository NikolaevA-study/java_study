package com.example.send_user_info_service.services;
import com.example.send_user_info_service.dto.ServiceMessageDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "app.messaging", havingValue = "kafka")
@RequiredArgsConstructor
public class KafkaProducerService implements MessageSender{

    private final KafkaTemplate<String, String> kafkaTemplate; // KafkaTemplate для отправки сообщений в Kafka
    private final ObjectMapper objectMapper;

    final String topic = "user-topic";

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);
    // Метод отправки пользователя в Kafka
    @Override
    public void send(ServiceMessageDTO userKafkaDTO) {
        try {
            // Отправляем сообщение в Kafka. Мы используем топик "user-topic".
            String msgJson = objectMapper.writeValueAsString(userKafkaDTO);
            SendResult<String, String> result = kafkaTemplate.send(topic, msgJson).get();
            logger.info(result.getRecordMetadata().toString());

        } catch (Exception e) {
            logger.error("Kafka send failed", e);
            throw new RuntimeException("Failed to send message to Kafka", e);
        }
    }
}