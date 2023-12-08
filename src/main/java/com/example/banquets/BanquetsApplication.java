package com.example.banquets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BanquetsApplication {

	public static void main(String[] args) {

		ApplicationContext applicationContext =  SpringApplication.run(BanquetsApplication.class, args);

	}

}
