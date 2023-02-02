package br.faccat.projeto.projetofaccat.configuration;

import org.springframework.amqp.core.*;
import org.springframework.stereotype.Component;

import org.springframework.context.annotation.Bean;


@Component
public class RabbitMqConfiguration {
    public static final String EXCHANGE_NAME = "topic-name";

    public static final String queueName = "EMAIL_QUEUE";

    public static final String ROUTING_KEY = "";


    @Bean
    public Exchange declareExchange() {
        return ExchangeBuilder.directExchange(EXCHANGE_NAME)
                .durable(true)
                .build();
    }
    @Bean
    public Queue declareQueue() {
        return QueueBuilder.durable(queueName)
                .build();
    }

    @Bean
    public Binding declareBinding(Exchange exchange, Queue queue) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(ROUTING_KEY)
                .noargs();
    }
}
