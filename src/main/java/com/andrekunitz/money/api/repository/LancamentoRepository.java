package com.andrekunitz.money.api.repository;

import com.andrekunitz.money.api.model.Lancamento;
import com.andrekunitz.money.api.repository.lancamento.LancamentoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {}
