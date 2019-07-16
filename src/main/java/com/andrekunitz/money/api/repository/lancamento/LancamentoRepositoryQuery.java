package com.andrekunitz.money.api.repository.lancamento;

import com.andrekunitz.money.api.dto.LancamentoEstatisticaCategoria;
import com.andrekunitz.money.api.dto.LancamentoEstatisticaDia;
import com.andrekunitz.money.api.model.Lancamento;
import com.andrekunitz.money.api.repository.filter.LancamentoFilter;
import com.andrekunitz.money.api.repository.projection.ResumoLancamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface LancamentoRepositoryQuery {

    public List<LancamentoEstatisticaCategoria> porCategoria(LocalDate mesReferencia);

    public List<LancamentoEstatisticaDia> porDia(LocalDate mesReferencia);

    public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);

    public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable);
}
