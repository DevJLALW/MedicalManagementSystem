package com.srh.medicalmanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MedicalManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalManagementSystemApplication.class, args);
	}

}
