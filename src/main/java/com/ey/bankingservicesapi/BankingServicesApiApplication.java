package com.ey.bankingservicesapi;

import com.ey.bankingservicesapi.models.LoggerTest;
import com.ey.bankingservicesapi.models.LombokTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankingServicesApiApplication {

	public static void main(String[] args) {
		LombokTest pr = new LombokTest("sssss");


		System.out.printf("pr+"+pr.getPCode());
		SpringApplication.run(BankingServicesApiApplication.class, args);
		LoggerTest.writeLogs();
	}

}
