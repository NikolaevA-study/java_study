package com.example.send_user_info_service.configurations;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;

public class RabbitConfig {

    // Названия очереди, обменника и routing key можно вынести в constants
    public static final String QUEUE = "user-info-service";
    public static final String EXCHANGE = "userExchange";
    public static final String ROUTING_KEY = "userRoutingKey";
    /**
     * Создаёт очередь с именем user.queue.
     * durable = true — очередь сохраняется при перезапуске RabbitMQ
     */
    @Bean
    public Queue queue() {
        return new Queue(QUEUE, true);
    }
    /**
     * Создаёт DirectExchange с именем user.exchange.
     * DirectExchange направляет сообщения в очередь по точному совпадению routing key.
     */
    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE);
    }
    /**
     * Связывает очередь и обменник по routing key.
     * Если routing key сообщения = user.routingKey — сообщение попадёт в user.queue.
     */
    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder
                .bind(queue)                 // что связываем
                .to(exchange)               // с чем связываем
                .with(ROUTING_KEY);         // по какому ключу
    }
}
