package com.example.demo.mock.server;

import org.mockserver.mock.action.HttpForwardActionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		new HttpForwardActionHandler();
		SpringApplication.run(Application.class, args);
	}
}
