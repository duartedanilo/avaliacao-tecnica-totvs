package com.totvs.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.totvs.api.models.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
