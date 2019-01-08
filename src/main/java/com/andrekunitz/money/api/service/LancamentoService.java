package com.andrekunitz.money.api.service;

import com.andrekunitz.money.api.model.Lancamento;
import com.andrekunitz.money.api.model.Pessoa;
import com.andrekunitz.money.api.repository.LancamentoRepository;
import com.andrekunitz.money.api.repository.PessoaRepository;
import com.andrekunitz.money.api.service.exception.PessoaInexistenteOuInativaExcepton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LancamentoService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private LancamentoRepository lancamentoRepository;

    public Lancamento salvar(Lancamento lancamento) {
        Pessoa pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo()).orElse(null);

        if (pessoa == null || pessoa.isInativo()) {
            throw new PessoaInexistenteOuInativaExcepton();
        }

        return lancamentoRepository.save(lancamento);
    }
}
