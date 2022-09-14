package br.com.challenge.alura.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.challenge.alura.models.Despesa;
import br.com.challenge.alura.models.Receita;

@Service
public class ResumoService {

	@Autowired
	private ReceitaService receitaService;

	@Autowired
	private DespesaService despesaService;

	private Double totalReceita = 0.0;
	private Double totalDespesa = 0.0;

	public Double totalReceita(Integer ano, Integer mes, Pageable pageable) {

		Page<Receita> listaReceita = receitaService.findAllByAnoAndMes(ano, mes, pageable);

		listaReceita.forEach(l -> totalReceita += l.getValor());

		return totalReceita;
	}

	public Double totalDespesa(Integer ano, Integer mes, Pageable pageable) {

		Page<Despesa> listaDespesa = despesaService.filtrarPorAnoMes(ano, mes, pageable);

		listaDespesa.forEach(l -> totalDespesa += l.getValor());

		return totalDespesa;
	}

}
