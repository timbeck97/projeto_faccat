package br.faccat.projeto.projetofaccat.consumer;

import br.faccat.projeto.projetofaccat.configuration.RabbitMqConfiguration;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

@Service
public class EmailConsumer {

    @RabbitListener(queues = RabbitMqConfiguration.queueName)
    public void consumer(String message){
        System.out.println("-----> READING MESSAGE <-----");
        System.out.println(message);

    }
}
