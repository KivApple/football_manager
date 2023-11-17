package com.eternal_search.football_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.eternal_search.football_manager.model.repository")
public class FootballManagerApplication {
	public static void main(String[] args) {
		SpringApplication.run(FootballManagerApplication.class, args);
	}
}
