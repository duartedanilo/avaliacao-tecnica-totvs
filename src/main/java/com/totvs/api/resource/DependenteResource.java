package com.totvs.api.resource;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.totvs.api.models.Dependente;
import com.totvs.api.models.Pessoa;
import com.totvs.api.repositories.DependenteRepository;
import com.totvs.api.repositories.PessoaRepository;

@RestController
@RequestMapping("/")
public class DependenteResource {
	
	@Autowired
	DependenteRepository dependenteRepository;
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	@PostMapping("/pessoa/{idPessoa}/dependente")
	public ResponseEntity<Object> store (
		@PathVariable(value="idPessoa") long idPessoa, 
		@RequestBody Dependente dependente
	) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(idPessoa);
		
		if(!pessoa.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		dependente.setPessoa(pessoa.get());
		
		Dependente novoDependente = dependenteRepository.save(dependente);
		
		return new ResponseEntity<>(novoDependente, HttpStatus.CREATED);
	}
	
	@PutMapping("/dependente/{id}")
	public ResponseEntity<Object> update(@PathVariable(value="id") long id, @RequestBody Dependente dependente) {
		Optional<Dependente> dependenteAntigo = dependenteRepository.findById(id);
		
		if(!dependenteAntigo.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		Dependente dependenteNovo = dependenteAntigo.get();
		
		dependenteNovo.setNome(dependente.getNome());
		dependenteNovo.setTipo(dependente.getTipo());
		
		Dependente updated = dependenteRepository.save(dependenteNovo);
		
		return new ResponseEntity<>(updated, HttpStatus.OK);
	}
	
	@DeleteMapping("/dependente/{id}")
	public ResponseEntity<Object> destroy(@PathVariable(value="id") long id) {
		Optional<Dependente> dependente = dependenteRepository.findById(id);
		
		if(!dependente.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		dependenteRepository.delete(dependente.get());
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
