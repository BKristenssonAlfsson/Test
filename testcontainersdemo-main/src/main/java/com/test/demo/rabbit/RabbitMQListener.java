package com.test.demo.rabbit;

import com.test.demo.message.MessageModel;
import com.test.demo.message.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;

@Configuration
public class RabbitMQListener {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQListener.class);
    private MessageService messageService;

    @Autowired
    public RabbitMQListener(MessageService messageService) {
        this.messageService = messageService;
    }

    @RabbitListener(queues = {"${message.rabbitmq.queue.name}"})
    public void receive(@Payload MessageModel payload) {
        logger.info("Payload: {}", payload);
        messageService.postNewMessage(payload);
    }

}
