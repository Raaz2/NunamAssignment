package com.nunam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = "com.nunam.model") // Adjust the package path
public class DataprocessingsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataprocessingsystemApplication.class, args);
	}

}
