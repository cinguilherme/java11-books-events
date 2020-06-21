package com.gcc.eventscore.configurations;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqConfig {

    public static final String BOOKS_EXCHANGE = "x-books-exchange";
    public static final String DEAD_LETTERS = "q-deadletters";
    public static final String NEW_BOOKS_QUEUE = "q-create-books";

    public static final String BOOKS_NOTIFICATION_EXCHANGE = "x-books-notification-exchange";
    public static final String BOOKS_NOTIFICATION_QUEUE = "q-books-notifications";

    @Bean
    Queue createBooksQueue() {
        return QueueBuilder.durable(NEW_BOOKS_QUEUE).build();
    }

    @Bean
    Queue booksNotifications() {return QueueBuilder.durable(BOOKS_NOTIFICATION_QUEUE).build();}

    @Bean
    Queue deadLetters() {
        return QueueBuilder.durable(DEAD_LETTERS).build();
    }

    @Bean
    Exchange booksExchange() {
        return ExchangeBuilder.topicExchange(BOOKS_EXCHANGE).build();
    }

    @Bean
    Exchange booksNotificationExchange() {return ExchangeBuilder.topicExchange(BOOKS_NOTIFICATION_EXCHANGE).build();}

    @Bean
    Binding bindingCreateBookQueue(Queue createBooksQueue, TopicExchange booksExchange) {
        return BindingBuilder.bind(createBooksQueue).to(booksExchange).with(NEW_BOOKS_QUEUE);
    }

    @Bean
    Binding bindingNotifications(Queue booksNotifications, TopicExchange booksNotificationExchange) {
        return BindingBuilder.bind(booksNotifications).to(booksNotificationExchange).with(NEW_BOOKS_QUEUE);
    }
}
