package br.com.challenge.alura.controllers;



import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.challenge.alura.dto.receita.ReceitaDTO;
import br.com.challenge.alura.dto.receita.ReceitaForm;
import br.com.challenge.alura.models.Receita;
import br.com.challenge.alura.services.ReceitaService;

@RestController
@RequestMapping("v1/receitas")
public class ReceitaController {

	@Autowired
	private ReceitaService receitaService;
	
	@PostMapping
	@CacheEvict(value = "receita", allEntries = true)
	public ResponseEntity<Object> salvar(@RequestBody @Valid ReceitaForm form){
		
		var receita = new Receita();
		
		BeanUtils.copyProperties(form, receita);
		
		if(receitaService.save(receita)) {
			return new ResponseEntity<>("Há duplicidade no cadastro mensal!", HttpStatus.CONFLICT);
		}else {
			return ResponseEntity.status(HttpStatus.CREATED).body(receita);
		}
	}
	
	@GetMapping("/{id}")
	@CacheEvict(value = "receita", allEntries = true)
	public ResponseEntity<Object> getReceita(@PathVariable Long id){
		var receita = receitaService.getReceita(id);
		var dto = new ReceitaDTO(receita);		
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@GetMapping
	@Cacheable(value = "receita")
	public ResponseEntity<Page<ReceitaDTO>> listaReceita(@RequestParam(required = false) String descricao,
			@PageableDefault(sort = "id" ,page = 0, size = 5,direction = Direction.ASC) Pageable pageable){
		
		try {
			if(descricao == null) {
				Page<Receita> receitas = receitaService.listarReceitas(pageable);
				
				return ResponseEntity.status(HttpStatus.OK).body(ReceitaDTO.returnReceitasDTO(receitas));
			}else{
				Page<Receita> receitas = receitaService.listarReceitasPorDescricao(descricao, pageable);
				
				return ResponseEntity.status(HttpStatus.OK).body(ReceitaDTO.returnReceitasDTO(receitas));
			}
		}catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/{ano}/{mes}")
    public ResponseEntity<Page<ReceitaDTO>> findAllByAnoAndMes(@PathVariable Integer ano, @PathVariable Integer mes, @PageableDefault(direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        Page<Receita> receitas = receitaService.findAllByAnoAndMes(ano, mes, paginacao);
        
        return ResponseEntity.status(HttpStatus.OK).body(ReceitaDTO.returnReceitasDTO(receitas));
	}
	
	@PutMapping("/{id}")
	@CacheEvict(value = "receita", allEntries = true)
	public ResponseEntity<Object> atualizarReceita(@PathVariable Long id, @RequestBody @Valid ReceitaForm form){

		var receita = new Receita();
		
		BeanUtils.copyProperties(form, receita);
		receitaService.atualizar(id, receita);
		
		return ResponseEntity.status(HttpStatus.OK).body("Receita alterada com sucesso!");
	}
	
	@DeleteMapping("/{id}")
	@CacheEvict(value = "receita", allEntries = true)
	public ResponseEntity<Object> excluirReceita(@PathVariable Long id){
			receitaService.excluirReceita(id);
		
			return ResponseEntity.ok("Receita excluída com sucesso!");
	}
}
