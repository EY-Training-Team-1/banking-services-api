package com.ey.bankingservicesapi;

import com.ey.bankingservicesapi.models.LoggerTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.ey")
public class BankingServicesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingServicesApiApplication.class, args);
		LoggerTest.writeLogs();
	}

}
