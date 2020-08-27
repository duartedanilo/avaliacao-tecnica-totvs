package com.totvs.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.totvs.api.models.Telefone;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> { }
