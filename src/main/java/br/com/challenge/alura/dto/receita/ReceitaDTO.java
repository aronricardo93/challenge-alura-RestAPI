package br.com.challenge.alura.dto.receita;

import java.time.LocalDate;

import org.springframework.data.domain.Page;

import br.com.challenge.alura.models.Receita;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReceitaDTO {

	private String descricao;
	
	private Double valor;
	
	private LocalDate data;
	
	
	public ReceitaDTO(Receita receita) {
		this.descricao = receita.getDescricao();
		this.valor = receita.getValor();
		this.data = receita.getData();
	}
	
	public static Page<ReceitaDTO> returnReceitasDTO(Page<Receita> receitas){
		return receitas.map(ReceitaDTO::new);
	}

}
