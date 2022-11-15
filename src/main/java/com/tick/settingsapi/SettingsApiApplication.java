package com.tick.settingsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SettingsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SettingsApiApplication.class, args);
	}

}
