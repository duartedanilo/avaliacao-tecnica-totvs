package com.totvs.api.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Pessoa implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String nome;
	
	private String cpf;
	
	private String profissao;
	
	private String salario;
	
	private String data_nascimento;
	
	@OneToMany(
		mappedBy = "pessoa",
		cascade = CascadeType.ALL,
		orphanRemoval = true
	)
	private List<Endereco> enderecos;
	
	@OneToMany(
		mappedBy = "pessoa",
		cascade = CascadeType.ALL,
		orphanRemoval = true
	)
	private List<Dependente> dependentes;
	
	@OneToMany(
		mappedBy="pessoa",
		cascade = CascadeType.ALL,
		orphanRemoval = true
	)
	private List<Telefone> telefones;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getProfissao() {
		return profissao;
	}
	
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}
	
	public String getSalario() {
		return salario;
	}
	
	public void setSalario(String salario) {
		this.salario = salario;
	}
	
	public String getData_nascimento() {
		return data_nascimento;
	}
	
	public void setData_nascimento(String data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public List<Dependente> getDependentes() {
		return dependentes;
	}
	
	public List<Telefone> getTelefones() {
		return telefones;
	}
}
