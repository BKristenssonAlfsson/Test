package com.test.demo.message;

import com.test.demo.rabbit.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private RabbitMQSender rabbitMQSender;
    @Autowired
    public MessageService(MessageRepository messageRepository, RabbitMQSender rabbitMQSender) {
        this.messageRepository = messageRepository;
        this.rabbitMQSender = rabbitMQSender;
    }

    public void sendNewMessage(MessageModel message){
        rabbitMQSender.send(message);
    }
    public void postNewMessage(MessageModel message) {
        Message newMessage = new Message();
        newMessage.setTest(message.getMessage());
        messageRepository.save(newMessage);
    }
}
