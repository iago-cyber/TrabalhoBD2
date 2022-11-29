package com.example.trabalhoiago.service;

import com.example.trabalhoiago.model.Endereco;
import com.example.trabalhoiago.repository.EnderecoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;


    public Endereco atualizar(Long id, Endereco endereco) {
        Endereco enderecoSalvo = buscarEnderecoPeloId(id);

        BeanUtils.copyProperties(endereco, enderecoSalvo, "id");
        return enderecoRepository.save(enderecoSalvo);
    }

    public Endereco buscarEnderecoPeloId(Long id) {
        Endereco enderecoSalvo = enderecoRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));

        return enderecoSalvo;
    }

}
