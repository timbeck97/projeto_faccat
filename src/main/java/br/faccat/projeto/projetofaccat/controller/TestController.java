package br.faccat.projeto.projetofaccat.controller;

import org.springframework.amqp.core.Message;
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


    @PostMapping(value = "/email")
    public ResponseEntity<String> sendMessage(@RequestBody String msg){
        System.out.println();
        rabbitTemplate.send("EMAIL_QUEUE", new Message(msg.getBytes()));

        return ResponseEntity.ok("ok");
    }
    @PostMapping(value = "/report")
    public ResponseEntity<String> sendMessageReport(@RequestBody String msg){
        System.out.println();
        rabbitTemplate.send("REPORT_QUEUE", new Message(msg.getBytes()));

        return ResponseEntity.ok("ok");
    }

}

