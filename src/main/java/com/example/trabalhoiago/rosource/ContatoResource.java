package com.example.trabalhoiago.rosource;

import com.example.trabalhoiago.event.RecursoCriadoEvent;
import com.example.trabalhoiago.model.Contato;
import com.example.trabalhoiago.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.trabalhoiago.repository.ContatoRepository;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contato")
public class ContatoResource {

    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private ContatoService contatoService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<Contato> listar() {
        return contatoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contato> buscarPeloId(@PathVariable Long id) {
        Optional<Contato> contato = contatoRepository.findById(id);

        return contato.isPresent() ? ResponseEntity.ok(contato.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Contato> criar(@Valid @RequestBody Contato contato, HttpServletResponse response) {
        Contato contatoSalvo = contatoRepository.save(contato);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, contatoSalvo.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(contatoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contato> atualizar(@PathVariable Long id, @Valid @RequestBody Contato contato) {
        Contato contatoSalvo = contatoService.atualizar(id, contato);
        return ResponseEntity.ok(contatoSalvo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        contatoRepository.deleteById(id);
    }


}
