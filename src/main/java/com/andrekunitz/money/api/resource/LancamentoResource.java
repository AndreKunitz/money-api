package com.andrekunitz.money.api.resource;

import com.andrekunitz.money.api.event.RecursoCriadoEvent;
import com.andrekunitz.money.api.exceptionhendler.MoneyExceptionHandler;
import com.andrekunitz.money.api.model.Lancamento;
import com.andrekunitz.money.api.repository.LancamentoRepository;
import com.andrekunitz.money.api.repository.filter.LancamentoFilter;
import com.andrekunitz.money.api.service.LancamentoService;
import com.andrekunitz.money.api.service.exception.PessoaInexistenteOuInativaExcepton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

    @Autowired
    LancamentoRepository lancamentoRepository;

    @Autowired
    LancamentoService lancamentoService;

    @Autowired
    ApplicationEventPublisher publisher;

    @Autowired
    MessageSource messageSource;

    @GetMapping
    public List<Lancamento> pesquisar(LancamentoFilter lancamentoFilter) {
        return lancamentoRepository.filtrar(lancamentoFilter);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Lancamento> bucarPeloCodigo(@PathVariable Long codigo) {
        Lancamento lancamentoProcurado = lancamentoRepository.findById(codigo).orElse(null);

        return lancamentoProcurado != null ? ResponseEntity.ok(lancamentoProcurado) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {
        Lancamento lancamentoSalvo = lancamentoService.salvar(lancamento);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getCodigo()));

        return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
    }

    @ExceptionHandler({ PessoaInexistenteOuInativaExcepton.class })
    public ResponseEntity<Object> handlePessoaInexistenteOuInativaExcepton(PessoaInexistenteOuInativaExcepton ex) {
        String menssagemUsuario = messageSource.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
        String menssagemDesenvolvedor = ex.toString();

        List<MoneyExceptionHandler.Erro> erros = Arrays.asList(new MoneyExceptionHandler.Erro(menssagemUsuario, menssagemDesenvolvedor));

        return ResponseEntity.badRequest().body(erros);
    }

}
