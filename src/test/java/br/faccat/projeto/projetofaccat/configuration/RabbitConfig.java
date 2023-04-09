package br.faccat.projeto.projetofaccat.configuration;

import org.mockito.Mockito;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
public class RabbitConfig {

    @Bean
    @Primary
    public CachingConnectionFactory rabbitAdmin() {
        return Mockito.mock(CachingConnectionFactory.class);
    }

}
