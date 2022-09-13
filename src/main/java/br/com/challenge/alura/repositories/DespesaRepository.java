package br.com.challenge.alura.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.challenge.alura.models.Despesa;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long>{

	Page<Despesa> findByDescricao(String descricao, Pageable pageable);

	@Query("SELECT d FROM Despesa d WHERE d.data BETWEEN ?1 AND ?2")
	Page<Despesa> filtrarPorAnoMes(LocalDate dataInicio, LocalDate dataFim, Pageable pageable);

}
