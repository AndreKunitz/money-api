package com.andrekunitz.money.api.repository;

import com.andrekunitz.money.api.model.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {}
