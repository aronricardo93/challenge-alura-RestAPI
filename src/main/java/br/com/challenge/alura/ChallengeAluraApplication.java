package br.com.challenge.alura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableCaching
public class ChallengeAluraApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeAluraApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("senha123"));
	}

}
