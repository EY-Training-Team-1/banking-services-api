package main.java.com.bank.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "main.java.com.bank")
@EnableJpaRepositories("main.java.com.bank.repositories")
@EntityScan("main.java.com.bank.models")
public class UserServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(UserServiceApplication.class, args);
	}

}