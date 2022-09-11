package br.com.challenge.alura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ChallengeAluraApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeAluraApplication.class, args);
	}

}
