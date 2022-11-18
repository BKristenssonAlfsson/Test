package com.test.demo.rabbit;

import com.test.demo.message.MessageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQSender.class);
    private AmqpTemplate rabbitTemplate;

    @Autowired
    public RabbitMQSender(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${message.rabbitmq.queue.exchange}")
    private String exchange;

    @Value("${message.rabbitmq.queue.routingkey}")
    private String routingKey;

    public void send(MessageModel message) {
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
        logger.info("Message sent!");
    }
}
