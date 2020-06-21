package com.gcc.eventscore.books;

import com.gcc.eventscore.books.models.Book;
import com.gcc.eventscore.configurations.RabbitMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BooksCreationEventListener {

    @Autowired
    private BooksMessageSender messageSender;

    @RabbitListener(queues = RabbitMqConfig.NEW_BOOKS_QUEUE)
    public void processCreateBookMessage(@Payload String body) {
        log.info("intaking message {}", body);
        Book newBook = Book.builder().name(body).id(1L).build();
        messageSender.sendBookNotification(newBook);
    }

}
