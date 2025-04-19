package com.cmd.WarehouseManager.AuthenticationService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
		"org.cmd.WarehouseManager",
		"com.cmd.WarehouseManager",
		"com.cmd.WarehouseManager.AuthenticationService.config;"
	}
)
public class AuthenticationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationServiceApplication.class, args);
	}

}
