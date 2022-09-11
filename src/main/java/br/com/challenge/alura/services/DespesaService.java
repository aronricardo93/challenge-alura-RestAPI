package br.com.challenge.alura.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.challenge.alura.models.Despesa;
import br.com.challenge.alura.repositories.DespesaRepository;

@Service
public class DespesaService {

	private DespesaRepository despesaRepository;
	
	public DespesaService(DespesaRepository despesaRepository) {
		this.despesaRepository = despesaRepository;
	}

	@Transactional
	public boolean salvarDespesa(Despesa despesa) {
		
		List<Despesa> despesas = despesaRepository.findAll();
		boolean duplicidade = false;
		
		for(Despesa d : despesas) {
			if((d.getDescricao().equals(despesa.getDescricao()) && (d.getData().getMonth().equals(despesa.getData().getMonth())))) {
				duplicidade = true;
			}
		}
		if(duplicidade == false) {
			despesaRepository.save(despesa);
		}
		
		return duplicidade;
	}

	public Despesa getDespesa(Long id) {
		Optional<Despesa> op = despesaRepository.findById(id);
		
		return op.orElseThrow();
	}

	public Page<Despesa> listarDespesas(Pageable pageable) {
		return despesaRepository.findAll(pageable);
	}

	@Transactional
	public Despesa atualizarDespesa(Long id, Despesa despesa) {
		Despesa despesaOriginal = this.getDespesa(id);
		despesa.setId(despesaOriginal.getId());
		return despesaRepository.save(despesa);
	}

	
	@Transactional
	public void excluirDespesa(Long id) {
		Despesa despesa = this.getDespesa(id);
		despesaRepository.delete(despesa);
	}

}
