package com.emporio.emporio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.emporio")
@EntityScan("com.emporio")
@EnableJpaRepositories("com.emporio")
public class EmporioApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmporioApplication.class, args);
	}

}
