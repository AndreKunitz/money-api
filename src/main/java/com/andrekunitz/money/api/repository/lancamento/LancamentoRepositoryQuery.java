package com.andrekunitz.money.api.repository.lancamento;

import com.andrekunitz.money.api.model.Lancamento;
import com.andrekunitz.money.api.repository.filter.LancamentoFilter;

import java.util.List;

public interface LancamentoRepositoryQuery {

    public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);
}
