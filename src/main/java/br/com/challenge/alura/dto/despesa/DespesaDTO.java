package br.com.challenge.alura.dto.despesa;

import java.time.LocalDate;

import org.springframework.data.domain.Page;

import br.com.challenge.alura.enums.CategorizacaoDespesas;
import br.com.challenge.alura.models.Despesa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DespesaDTO {

	private String descricao;
	
	private Double valor;
	
	private LocalDate data;
	
	private CategorizacaoDespesas categorizacaoDespesas;
	
	public DespesaDTO(Despesa despesa) {
		this.descricao = despesa.getDescricao();
		this.valor = despesa.getValor();
		this.data = despesa.getData();
		this.categorizacaoDespesas = despesa.getCategorizacaoDespesas();
	}
	
	public static Page<DespesaDTO> returnDespesasDTO(Page<Despesa> despesas){
		return despesas.map(DespesaDTO::new);
	}
}
