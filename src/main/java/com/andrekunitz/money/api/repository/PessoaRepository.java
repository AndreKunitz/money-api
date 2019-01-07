package com.andrekunitz.money.api.repository;

import com.andrekunitz.money.api.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {}
