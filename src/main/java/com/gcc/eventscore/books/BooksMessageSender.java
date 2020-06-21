package com.gcc.eventscore.books;

import com.gcc.eventscore.books.models.Book;
import com.gcc.eventscore.configurations.RabbitMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BooksMessageSender {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    BooksMessageSender(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendBookNotification(Book book){
        log.info("producing message book notification {}", book);
        rabbitTemplate.convertAndSend(RabbitMqConfig.BOOKS_NOTIFICATION_QUEUE,book);
    }
}
