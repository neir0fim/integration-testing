package com.kuzin.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages =
		{"com.integration.domain",
		"com.integration.rest",
		"com.integration.persistence",
		"com.kuzin.integrationtesting"}
)
public class IntegrationTestingApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntegrationTestingApplication.class, args);
	}

}
