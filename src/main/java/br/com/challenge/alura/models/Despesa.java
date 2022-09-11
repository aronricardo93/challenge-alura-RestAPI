package br.com.challenge.alura.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.challenge.alura.enums.CategorizacaoDespesas;
import lombok.Data;


@Entity
@Data
@Table(name = "Despesas")
public class Despesa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String descricao;
	
	private Double valor;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;
	
	@Enumerated(EnumType.STRING)
	private CategorizacaoDespesas categorizacaoDespesas;
}
