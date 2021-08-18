package com.swirepay.bankingApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.swirepay.bankingApp"})
public class BankingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingAppApplication.class, args);
	}

}
