package br.com.challenge.alura.repositories;


import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.challenge.alura.models.Receita;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long>{

	Page<Receita> findByDescricao(String descricao, Pageable pageable);

	@Query("SELECT r FROM Receita r WHERE r.data BETWEEN :dataInicio AND :dataFinal")
	Page<Receita> findAllByAnoAndMes(@Param("dataInicio") LocalDate dataInicio, @Param("dataFinal") LocalDate dataFinal, Pageable pageable);

}
