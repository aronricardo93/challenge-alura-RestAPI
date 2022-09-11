package br.com.challenge.alura.dto.receita;



import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ReceitaForm {

	@NotBlank(message = "O campo descrição deve ser preenchido!")
	private String descricao;
	
	@NotNull(message = "O campo valor não pode ser nulo!")
	private Double valor;
	
	@NotNull(message = "O campo data deve ser preenchido!")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;
}
