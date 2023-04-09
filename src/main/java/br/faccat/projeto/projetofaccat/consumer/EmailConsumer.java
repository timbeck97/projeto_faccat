package br.faccat.projeto.projetofaccat.consumer;

import br.faccat.projeto.projetofaccat.configuration.RabbitMqConfiguration;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

@Service
public class EmailConsumer {


    @RabbitListener(queues = "EMAIL_QUEUE")
    public void consumer(String message){
        System.out.println("-----> READING MESSAGE EMAIL QUEUE <-----");
        System.out.println(message);

    }
    @RabbitListener(queues = "REPORT_QUEUE")
    public void consumerReport(String message){
        System.out.println("-----> READING MESSAGE REPORT QUEUE <-----");
        System.out.println(message);

    }
}
