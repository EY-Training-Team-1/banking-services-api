package com.ey.bankaccounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.ey")
@EntityScan("com.ey.bankaccounts.models")
@EnableJpaRepositories("com.ey.bankaccounts.repositories")
public class BankAccountsApplication {

	public static void main(String[] args) {

		SpringApplication.run(BankAccountsApplication.class, args);
	}

}
