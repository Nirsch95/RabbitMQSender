package com.sofka.demoRabbitSender.controller;

import com.sofka.demoRabbitSender.model.Message;
import com.sofka.demoRabbitSender.service.RabbitSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/rabbitmq/")
public class Controller {
    @Autowired
    private RabbitSender rabbitSender;

    @PostMapping(value = "/producer")
    public String producer(@RequestBody Message message) throws IOException {
        rabbitSender.send(message);
        return "Message sent to the RabbitMQ Successfully";
    }
}
