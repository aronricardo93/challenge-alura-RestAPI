package br.com.challenge.alura.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.challenge.alura.models.Receita;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long>{

	Page<Receita> findByDescricao(String descricao, Pageable pageable);

}
