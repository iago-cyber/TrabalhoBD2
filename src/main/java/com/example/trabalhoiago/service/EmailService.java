package com.example.trabalhoiago.service;

import com.example.trabalhoiago.model.Email;
import com.example.trabalhoiago.repository.EmailRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private EmailRepository emailRepository;


    public Email atualizar(Long id, Email email) {
        Email emailSalvo = buscarEmailPeloId(id);

        BeanUtils.copyProperties(email, emailSalvo, "id");
        return emailRepository.save(emailSalvo);
    }

    public Email buscarEmailPeloId(Long id) {
        Email emailSalvo = emailRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException((1)));

        return emailSalvo;
    }

}
