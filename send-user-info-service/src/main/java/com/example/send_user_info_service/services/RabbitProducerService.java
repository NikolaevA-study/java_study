package com.example.send_user_info_service.services;

import com.example.send_user_info_service.configurations.RabbitConfig;
import com.example.send_user_info_service.dto.ServiceMessageDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "app.messaging", havingValue = "rabbit")
@RequiredArgsConstructor
public class RabbitProducerService implements MessageSender {
    private final RabbitTemplate rabbitTemplate;
    @Override
    public void send(ServiceMessageDTO userRabbitDTO) {
        try {

            String msgJson = new ObjectMapper().writeValueAsString(userRabbitDTO);
            rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, RabbitConfig.ROUTING_KEY, msgJson);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
