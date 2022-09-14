package br.com.challenge.alura.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.challenge.alura.services.ResumoService;

@RestController
@RequestMapping("v1/resumo")
public class ResumoController {

	@Autowired
	private ResumoService resumoService;
	
	
	@GetMapping("/{ano}/{mes}")
	public ResponseEntity<Object> resumoDoMes(@PathVariable Integer ano, @PathVariable Integer mes ,Pageable pageable){
		
		Double valorTotalReceita = resumoService.totalReceita(ano, mes, pageable);
		Double valorTotalDespesa = resumoService.totalDespesa(ano, mes, pageable);
		Double saldo = valorTotalReceita - valorTotalDespesa;
		
		return ResponseEntity.ok("Valor total das receitas no mês: R$ " + valorTotalReceita
				+ "\nValor total das despesas no mês: R$ " + valorTotalDespesa
				+ "\nSaldo final do mês: R$ " + saldo);
	}
	
}
