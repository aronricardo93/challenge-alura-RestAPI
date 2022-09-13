package br.com.challenge.alura.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.challenge.alura.models.Receita;
import br.com.challenge.alura.repositories.ReceitaRepository;

@Service
public class ReceitaService {

	@Autowired
	private ReceitaRepository receitaRepository;
	
	private Page<Receita> receitas;
	
	@Transactional
	public boolean save(Receita receita) {
		List<Receita> receitas = receitaRepository.findAll();
		boolean duplicidade = false;
		
		for(Receita r : receitas) {
			if((r.getDescricao().equals(receita.getDescricao()) && (r.getData().getMonth().equals(receita.getData().getMonth())))) {
				duplicidade = true;
			}
		}
		if(duplicidade == false) {
			receitaRepository.save(receita);
		}
		
		return duplicidade;
	}

	public Page<Receita> listarReceitas(Pageable pageble) {
		return receitaRepository.findAll(pageble);
	}

	public Receita getReceita(Long id) {
		
		Optional<Receita> op = receitaRepository.findById(id);
		
		return op.orElseThrow();
	}

	@Transactional
	public void excluirReceita(Long id) {
		Receita originalReceita = this.getReceita(id);
		receitaRepository.delete(originalReceita);
	
	}

	@Transactional
	public Receita atualizar(Long id, Receita receita) {
		Receita alteracaoReceita = this.getReceita(id);
		receita.setId(alteracaoReceita.getId());
	
		return receitaRepository.save(receita);
	}

	public Page<Receita> listarReceitasPorDescricao(String descricao, Pageable pageable) {
		
		return receitaRepository.findByDescricao(descricao, pageable);
	}


	public Page<Receita> findAllByAnoAndMes(Integer ano, Integer mes, Pageable pageable) {
		
		LocalDate data = LocalDate.of(ano, mes, 1);
		LocalDate dataInicio = data.withDayOfMonth(1);
		LocalDate dataFinal = data.withDayOfMonth(data.lengthOfMonth());
		
		return receitaRepository.findAllByAnoAndMes(dataInicio, dataFinal, pageable);

	}
}