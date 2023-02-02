package br.faccat.projeto.projetofaccat.controller;

import br.faccat.projeto.projetofaccat.configuration.RabbitMqConfiguration;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/teste")
public class TestController {
    @Autowired
    private RabbitTemplate rabbitTemplate;


    @PostMapping
    public ResponseEntity<String> sendMessage(@RequestBody String msg){
        System.out.println();
        rabbitTemplate.convertAndSend(RabbitMqConfiguration.EXCHANGE_NAME, "", msg);

        return ResponseEntity.ok("ok");
    }

}

