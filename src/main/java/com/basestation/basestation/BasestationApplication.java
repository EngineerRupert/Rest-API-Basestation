package com.basestation.basestation;

import com.basestation.basestation.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BasestationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppConfig.class, args);
	}

}
