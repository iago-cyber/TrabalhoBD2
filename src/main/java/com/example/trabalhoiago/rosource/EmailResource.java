package com.example.trabalhoiago.rosource;

import com.example.trabalhoiago.event.RecursoCriadoEvent;
import com.example.trabalhoiago.model.Email;
import com.example.trabalhoiago.repository.EmailRepository;
import com.example.trabalhoiago.service.EmailService;
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
@RequestMapping("/email")
public class EmailResource {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<Email> listar() {
        return emailRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Email> buscarPeloId(@PathVariable Long id) {
        Optional<Email> email = emailRepository.findById(id);

        return email.isPresent() ? ResponseEntity.ok(email.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Email> criar(@Valid @RequestBody Email email, HttpServletResponse response) {
        Email emailSalvo = emailRepository.save(email);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, emailSalvo.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(emailSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Email> atualizar(@PathVariable Long id, @Valid @RequestBody Email email) {
        Email emailSalvo = emailService.atualizar(id, email);
        return ResponseEntity.ok(emailSalvo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        emailRepository.deleteById(id);
    }

}
