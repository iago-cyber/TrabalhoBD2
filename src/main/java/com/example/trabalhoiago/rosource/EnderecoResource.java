package com.example.trabalhoiago.rosource;

import com.example.trabalhoiago.event.RecursoCriadoEvent;
import com.example.trabalhoiago.model.Endereco;
import com.example.trabalhoiago.repository.EnderecoRepository;
import com.example.trabalhoiago.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/endereco")
public class EnderecoResource {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<Endereco> listar() {
        return enderecoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> buscarPeloId(@PathVariable Long id) {
        Optional<Endereco> endereco = enderecoRepository.findById(id);
        return endereco.isPresent() ? ResponseEntity.ok(endereco.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Endereco> criar(@Valid @RequestBody Endereco endereco, HttpServletResponse response) {
        Endereco enderecoSalvo = enderecoRepository.save(endereco);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, enderecoSalvo.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoSalvo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        enderecoRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Endereco> atualizar(@PathVariable Long id, @Valid @RequestBody Endereco endereco) {
        Endereco enderecoSalvo = enderecoService.atualizar(id, endereco);
        return ResponseEntity.ok(enderecoSalvo);
    }



}
