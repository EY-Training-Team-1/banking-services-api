package com.ey.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableFeignClients(basePackages = "com.ey")
@SpringBootApplication(scanBasePackages = "com.ey")
@EnableJpaRepositories("com.ey.repositories")
@EntityScan("com.ey.models")
public class AccountApplication {

	public static void main(String[] args) {

		SpringApplication.run(AccountApplication.class, args);
	}

}
