package com.example.TechnoTroveProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableCaching
public class TechnoTroveProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechnoTroveProjectApplication.class, args);
	}
}
