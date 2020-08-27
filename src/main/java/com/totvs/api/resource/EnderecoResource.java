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

import com.totvs.api.models.Endereco;
import com.totvs.api.models.Pessoa;
import com.totvs.api.repositories.EnderecoRepository;
import com.totvs.api.repositories.PessoaRepository;

@RestController
@RequestMapping("/")
public class EnderecoResource {
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	@PostMapping("/pessoa/{idPessoa}/endereco")
	public ResponseEntity<Object> store(@PathVariable(value="idPessoa") long idPessoa, @RequestBody Endereco endereco ) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(idPessoa);
		
		if(!pessoa.isPresent()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		endereco.setPessoa(pessoa.get());
		
		Endereco enderecoCriado = enderecoRepository.save(endereco);
		
		return new ResponseEntity<>(enderecoCriado, HttpStatus.CREATED);
	}
}
