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
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		endereco.setPessoa(pessoa.get());
		
		Endereco enderecoCriado = enderecoRepository.save(endereco);
		
		return new ResponseEntity<>(enderecoCriado, HttpStatus.CREATED);
	}
	
	@PutMapping("/endereco/{id}")
	public ResponseEntity<Object> update(@PathVariable(value="id") long id, @RequestBody Endereco endereco) {
		Optional<Endereco> enderecoAntigo = enderecoRepository.findById(id);
		
		if(!enderecoAntigo.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		Endereco enderecoNovo = enderecoAntigo.get();
		
		enderecoNovo.setTipoEndereco(endereco.getTipoEndereco());
		enderecoNovo.setTipo(endereco.getTipo());
		enderecoNovo.setNome(endereco.getNome());
		enderecoNovo.setNumero(endereco.getNumero());
		enderecoNovo.setComplemento(endereco.getComplemento());
		enderecoNovo.setCep(endereco.getCep());
		enderecoNovo.setBairro(endereco.getBairro());
		enderecoNovo.setCidade(endereco.getCidade());
		enderecoNovo.setEstado(endereco.getEstado());
		enderecoNovo.setPais(endereco.getPais());
		
		Endereco updated = enderecoRepository.save(enderecoNovo);
		
		return new ResponseEntity<>(updated, HttpStatus.OK);
	}
	
	@DeleteMapping("/endereco/{id}")
	public ResponseEntity<Object> destroy(@PathVariable(value="id") long id) {
		Optional<Endereco> endereco = enderecoRepository.findById(id);
		
		if(!endereco.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		enderecoRepository.delete(endereco.get());
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
