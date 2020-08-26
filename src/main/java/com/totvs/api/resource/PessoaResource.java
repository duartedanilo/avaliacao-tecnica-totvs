package com.totvs.api.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.totvs.api.models.Pessoa;
import com.totvs.api.repositories.PessoaRepository;

@RestController
@RequestMapping(value="/")
public class PessoaResource {

	@Autowired
	PessoaRepository pessoaRepository; 
	
	@GetMapping("/pessoa")
	public List<Pessoa> index() {
		return pessoaRepository.findAll();
	}
	
	@GetMapping("/pessoa/{id}")
	public ResponseEntity<Object> show(@PathVariable(value="id") long id) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(id);
				
		if(!pessoa.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
			
		return new ResponseEntity<>(pessoa.get(), HttpStatus.OK);
	}
	
	@PostMapping("/pessoa")
	public Pessoa store(@RequestBody Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}
	
	@PutMapping("/pessoa/{id}")
	public ResponseEntity<Object> update(@PathVariable(value="id") long id, @RequestBody Pessoa pessoa) {
		Optional<Pessoa> pessoaAntiga = pessoaRepository.findById(id);
		
		if(!pessoaAntiga.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		Pessoa pessoaNova = pessoaAntiga.get();
		
		pessoaNova.setNome(pessoa.getNome());
		pessoaNova.setCpf(pessoa.getProfissao());
		pessoaNova.setProfissao(pessoa.getProfissao());
		pessoaNova.setSalario(pessoa.getSalario());
		pessoaNova.setData_nascimento(pessoa.getData_nascimento());
		
		Pessoa created = pessoaRepository.save(pessoaNova);
		
		return new ResponseEntity<>(created, HttpStatus.OK);
	}
	
	@DeleteMapping("/pessoa/{id}")
	public ResponseEntity<Object> destroy(@PathVariable(value="id") long id) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(id);
		
		if(!pessoa.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		pessoaRepository.delete(pessoa.get());
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
