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

import com.totvs.api.models.Pessoa;
import com.totvs.api.models.Telefone;
import com.totvs.api.repositories.PessoaRepository;
import com.totvs.api.repositories.TelefoneRepository;

@RestController
@RequestMapping("/")
public class TelefoneResource {

	@Autowired
	TelefoneRepository telefoneRepository;
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	@PostMapping("/pessoa/{idPessoa}/telefone")
	public ResponseEntity<Object> store (
		@PathVariable(value="idPessoa") long idPessoa,
		@RequestBody Telefone telefone
	) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(idPessoa);
		
		if(!pessoa.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		telefone.setPessoa(pessoa.get());
		
		Telefone newTelefone = telefoneRepository.save(telefone);
		
		return new ResponseEntity<>(newTelefone, HttpStatus.CREATED);
	}
}
