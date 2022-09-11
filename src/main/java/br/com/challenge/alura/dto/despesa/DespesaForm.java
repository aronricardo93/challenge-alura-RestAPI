package br.com.challenge.alura.dto.despesa;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.challenge.alura.enums.CategorizacaoDespesas;
import lombok.Data;

@Data
public class DespesaForm {
	
	@NotBlank(message = "O campo descrição deve ser preenchido!")
	private String descricao;
	
	@NotNull(message = "O campo valor não pode ser nulo!")
	private Double valor;
	
	@NotNull(message = "O campo data deve ser preenchido!")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;
	
	private CategorizacaoDespesas categorizacaoDespesas = CategorizacaoDespesas.OUTRAS;
}
