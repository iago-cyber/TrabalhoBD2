package com.example.trabalhoiago.rosource;

import com.example.trabalhoiago.event.RecursoCriadoEvent;
import com.example.trabalhoiago.model.Telefone;
import com.example.trabalhoiago.repository.TelefoneRepository;
import com.example.trabalhoiago.service.TelefoneService;
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
@RequestMapping("/telefone")
public class TelefoneResource {

    @Autowired
    private TelefoneRepository telefoneRepository;

    @Autowired
    private TelefoneService telefoneService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<Telefone> listar() {
        return telefoneRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Telefone> buscarPeloId(@PathVariable Long id) {
        Optional<Telefone> telefone = telefoneRepository.findById(id);
        return telefone.isPresent() ? ResponseEntity.ok(telefone.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Telefone> criar(@Valid @RequestBody Telefone telefone, HttpServletResponse response) {
        Telefone telefoneSalvo = telefoneRepository.save(telefone);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, telefoneSalvo.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(telefoneSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Telefone> atualizar(@PathVariable Long id, @Valid @RequestBody Telefone telefone) {
        Telefone telefoneSalvo = telefoneService.atualizar(id, telefone);
        return ResponseEntity.ok(telefoneSalvo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        telefoneRepository.deleteById(id);
    }


}
