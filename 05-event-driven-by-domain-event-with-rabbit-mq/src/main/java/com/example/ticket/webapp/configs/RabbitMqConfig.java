package com.example.ticket.webapp.configs;

import com.example.ticket.app.application.notification.NotificationApplicationService;
import com.example.ticket.webapp.consumers.NotificationConsumer;
import com.example.ticket.webapp.producer.Producer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("debug")
public class RabbitMqConfig {
    // Remove this to send as binary
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Producer notificationProducer(RabbitTemplate rabbitTemplate) {
        return new Producer(rabbitTemplate, "notificationEx", "notification");
    }

    @Bean
    public NotificationConsumer notificationConsumer(NotificationApplicationService notificationApplicationService) {
        return new NotificationConsumer(notificationApplicationService);
    }
}
