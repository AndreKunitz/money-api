package com.andrekunitz.money.api.repository;

import com.andrekunitz.money.api.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    List<Cidade> findByEstadoCodigo(Long estadoCodigo);
}
