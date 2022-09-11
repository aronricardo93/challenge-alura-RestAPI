package br.com.challenge.alura.controllers;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.challenge.alura.dto.despesa.DespesaDTO;
import br.com.challenge.alura.dto.despesa.DespesaForm;
import br.com.challenge.alura.dto.receita.ReceitaForm;
import br.com.challenge.alura.models.Despesa;
import br.com.challenge.alura.services.DespesaService;

@RestController
@RequestMapping("v1/despesas")
public class DespesaController {

	private DespesaService despesaService;
	
	public DespesaController(DespesaService despesaService) {
		this.despesaService = despesaService;
	}
	
	@PostMapping
	@CacheEvict(value = "despesas", allEntries = true)
	public ResponseEntity<Object> salvarDespesa(@RequestBody @Valid DespesaForm form){
		var despesa = new Despesa();
		
		BeanUtils.copyProperties(form, despesa);
		
		if(despesaService.salvarDespesa(despesa)) {
			return new ResponseEntity<>("Há duplicidade no cadastro mensal!", HttpStatus.CONFLICT);
		}else {
			return ResponseEntity.status(HttpStatus.CREATED).body(despesa);
		}
	}
	
	@GetMapping("/{id}")
	@CacheEvict(value = "despesas", allEntries = true)
	public ResponseEntity<Object> despesa(@PathVariable Long id){
		
		try {
			var dto = new DespesaDTO();
			Despesa despesa = despesaService.getDespesa(id);
			
			BeanUtils.copyProperties(despesa, dto);
			
			return ResponseEntity.status(HttpStatus.OK).body(dto);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Despesa não existente!");
		}
		
	}
	
	
	@GetMapping
	@Cacheable(value = "despesas")
	public ResponseEntity<Object> listaDespesas(@PageableDefault(sort = "id", page = 0, direction = Direction.ASC,size = 5)
	Pageable pageable){
	
		try {
			Page<Despesa> despesas = despesaService.listarDespesas(pageable);
			
			if(!despesas.isEmpty()) {
				return new ResponseEntity<>(despesas, HttpStatus.OK);
			}else {
				return new ResponseEntity<>("Lista de despesas vazia!", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/{id}")
	@CacheEvict(value = "despesas", allEntries = true)
	public ResponseEntity<Object> atualizar(@RequestBody @Valid ReceitaForm form, @PathVariable Long id){
		var despesa = new Despesa();
		BeanUtils.copyProperties(form, despesa);
		
		despesaService.atualizarDespesa(id, despesa);
		
		return ResponseEntity.ok().body("Despesa alterada com sucesso!");
	}
	
	@DeleteMapping("/{id}")
	@CacheEvict(value = "despesas", allEntries = true)
	public ResponseEntity<Object> excluir(@PathVariable Long id){
		try {
			despesaService.excluirDespesa(id);
			
			return ResponseEntity.status(HttpStatus.OK).body("Despesa excluída com sucesso!");
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nenhuma despesa encontrada!");
		}
	}
}
