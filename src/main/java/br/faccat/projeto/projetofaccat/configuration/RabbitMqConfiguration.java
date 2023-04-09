package br.faccat.projeto.projetofaccat.configuration;

import org.mockito.Mockito;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import org.springframework.context.annotation.Bean;


@Component
public class RabbitMqConfiguration {
    public static final String EXCHANGE_NAME = "topic-name";

    public static final String ROUTING_KEY = "";


    @Bean
    public Exchange declareExchange() {
        return ExchangeBuilder.directExchange(EXCHANGE_NAME)
                .durable(true)
                .build();
    }
    @Bean(name = "EMAIL")
    Queue declaraEmailQueue() {
        return QueueBuilder.durable("EMAIL_QUEUE")
                .build();
    }
    @Bean(name = "REPORT")
    Queue declareReportQueue() {
        return QueueBuilder.durable("REPORT_QUEUE")
                .build();
    }

    @Bean
    Binding emailBinding(@Qualifier("EMAIL")Queue emailQueue, DirectExchange exchange){
        return BindingBuilder.bind(emailQueue).to(exchange).with("BISCOITO_EMAIL");
    }

    @Bean
    Binding reortBinding(@Qualifier("REPORT")Queue emailQueue, DirectExchange exchange){
        return BindingBuilder.bind(emailQueue).to(exchange).with("BISCOITO_REPORT");
    }



}
