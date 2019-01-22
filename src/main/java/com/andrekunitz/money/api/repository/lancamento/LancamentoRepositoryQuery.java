package com.andrekunitz.money.api.repository.lancamento;

import com.andrekunitz.money.api.model.Lancamento;
import com.andrekunitz.money.api.repository.filter.LancamentoFilter;
import com.andrekunitz.money.api.repository.projection.ResumoLancamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LancamentoRepositoryQuery {

    public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
    public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable);
}
