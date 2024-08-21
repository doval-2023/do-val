package com.dov.sbapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SbappApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbappApplication.class, args);
	}

}
