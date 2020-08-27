package com.totvs.api.resource;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
}
