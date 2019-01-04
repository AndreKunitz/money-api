package com.andrekunitz.money.api.resource;

import com.andrekunitz.money.api.event.RecursoCriadoEvent;
import com.andrekunitz.money.api.model.Pessoa;
import com.andrekunitz.money.api.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

    @Autowired
    PessoaRepository pessoaRepository;

    @GetMapping
    public List<Pessoa> listar() {
        return pessoaRepository.findAll();
    }

    @Autowired
    ApplicationEventPublisher publisher;

    @PostMapping
    public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa novaPessoa, HttpServletResponse response) {
        Pessoa pessoaSalva = pessoaRepository.save(novaPessoa);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getCodigo()));

        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Pessoa> buscarPeloCodigo(@PathVariable Long codigo) {
        Pessoa pessoaProcurada = pessoaRepository.findById(codigo).orElse(null);

        return pessoaProcurada != null ? ResponseEntity.ok(pessoaProcurada) : ResponseEntity.notFound().build();
    }

}
