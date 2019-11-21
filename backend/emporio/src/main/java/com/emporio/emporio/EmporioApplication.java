package com.emporio.emporio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class EmporioApplication {

	@RequestMapping("/")
	public String name() {
		return "ciao";
	}

	public static void main(String[] args) {
		SpringApplication.run(EmporioApplication.class, args);
	}

}
