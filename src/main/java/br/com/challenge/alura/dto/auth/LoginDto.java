package br.com.challenge.alura.dto.auth;

import javax.validation.constraints.NotEmpty;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.Data;

@Data
public class LoginDto {
	
	@NotEmpty
	private String login;
	@NotEmpty(message = "Este campo não dever ficar vazio!")
	private String senha;
	
	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(login, senha);
	}
	
}
