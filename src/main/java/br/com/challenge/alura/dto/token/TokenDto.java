package br.com.challenge.alura.dto.token;

import lombok.Getter;

@Getter
public class TokenDto {

	private String token;
	private String tipo;
	
	public TokenDto(String token, String tipo) {
		this.tipo = tipo;
		this.token = token;
	}

}
