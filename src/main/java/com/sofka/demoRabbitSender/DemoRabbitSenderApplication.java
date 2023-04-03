package com.sofka.demoRabbitSender;


import com.sofka.demoRabbitSender.model.Message;
import com.sofka.demoRabbitSender.service.RabbitSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

@SpringBootApplication
public class DemoRabbitSenderApplication implements CommandLineRunner {

	@Autowired
	private RabbitSender rabbitSender;
	public static void main(String[] args) {
		SpringApplication.run(DemoRabbitSenderApplication.class, args);
	}

	@Override
	public void run(String... args){
		process("Sender-1");

		process("Sender-2");

		process("Sender-3");
	}

	private void process(String s) {
		new Thread(() -> {
			Random random = new Random();
			for (long i = 0; ; i++) {
				String message = "You have a new message with no " + i;
				rabbitSender.send(new Message(message));

				try {
					Thread.sleep(random.nextInt((15000 - 4000) + 1) + 4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}, s).start();
	}
}
