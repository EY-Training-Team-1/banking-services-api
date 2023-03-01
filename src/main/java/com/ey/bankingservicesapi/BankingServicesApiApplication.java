package com.ey.bankingservicesapi;

import com.ey.bankingservicesapi.models.LoggerTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.ey.bankingservicesapi")
@EntityScan("com.ey.bankingservicesapi.models")
@EnableJpaRepositories("com.ey.bankingservicesapi.repositories")
public class BankingServicesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingServicesApiApplication.class, args);
		LoggerTest.writeLogs();
	}

}
