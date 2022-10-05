package br.com.challenge.alura.config.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfigurations{

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.httpBasic()
			.and()
			.authorizeHttpRequests()
			.antMatchers("/swagger-ui/**").permitAll()
			.antMatchers(HttpMethod.GET, "/v1/receitas/**").permitAll()
			.antMatchers(HttpMethod.POST, "/v1/receitas").hasRole("ADMIN")
			.anyRequest().authenticated()
			.and()
			.csrf().disable();
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEnconder() {
		return new BCryptPasswordEncoder();
	}
	
	 @Bean
	 public WebSecurityCustomizer webSecurityCustomizer() {
		 return (web) -> web
				 .ignoring()
				 .antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
	 }
}

