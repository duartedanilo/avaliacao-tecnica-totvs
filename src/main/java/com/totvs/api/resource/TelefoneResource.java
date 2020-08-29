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
	
	@PutMapping("/telefone/{id}")
	public ResponseEntity<Object> update(@PathVariable(value="id") long id, @RequestBody Telefone telefone) {
		Optional<Telefone> telefoneAntigo = telefoneRepository.findById(id);
		
		if(!telefoneAntigo.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		Telefone telefoneNovo = telefoneAntigo.get();
		
		telefoneNovo.setCodigoPais(telefone.getCodigoPais());
		telefoneNovo.setDdd(telefone.getDdd());
		telefoneNovo.setNumero(telefone.getNumero());
		telefoneNovo.setTipo(telefone.getTipo());
		
		Telefone updated = telefoneRepository.save(telefoneNovo);
		
		return new ResponseEntity<>(updated, HttpStatus.OK);
	}
	
	@DeleteMapping("/telefone/{id}")
	public ResponseEntity<Object> destroy(@PathVariable(value="id") long id) {
		Optional<Telefone> telefone = telefoneRepository.findById(id);
		
		if(!telefone.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		telefoneRepository.delete(telefone.get());
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
