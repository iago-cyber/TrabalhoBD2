package com.example.trabalhoiago.service;

import com.example.trabalhoiago.model.Contato;
import com.example.trabalhoiago.repository.ContatoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;


    public Contato atualizar(Long id, Contato contato) {
        Contato contatoSalvo = buscarContatoPeloId(id);

        BeanUtils.copyProperties(contato, contatoSalvo, "id");
        return contatoRepository.save(contatoSalvo);
    }

    public Contato buscarContatoPeloId(Long id) {
        Contato contatoSalvo = contatoRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException((1)));

        return contatoSalvo;
    }
}
