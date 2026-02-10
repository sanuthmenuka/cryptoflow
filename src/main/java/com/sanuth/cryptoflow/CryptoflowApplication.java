package com.sanuth.cryptoflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CryptoflowApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptoflowApplication.class, args);
	}

}
