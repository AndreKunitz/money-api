package com.andrekunitz.money.api.repository;

import com.andrekunitz.money.api.model.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    public Page<Pessoa> findByNomeContaining(String nome, Pageable pageable);

}
