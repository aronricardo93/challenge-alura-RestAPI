package br.com.challenge.alura.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.challenge.alura.models.Despesa;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long>{

	//boolean existsByDescricaoAndData(String descricao, LocalDate data);

}
