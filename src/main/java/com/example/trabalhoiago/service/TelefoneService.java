package com.example.trabalhoiago.service;

import com.example.trabalhoiago.model.Telefone;
import com.example.trabalhoiago.repository.TelefoneRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class TelefoneService {

    @Autowired
    private TelefoneRepository telefoneRepository;

    public Telefone atualizar(Long id, Telefone telefone) {
        Telefone telefoneSalvo = buscarTelefonePeloId(id);
        BeanUtils.copyProperties(telefone, telefoneSalvo, "id");
        return telefoneRepository.save(telefoneSalvo);
    }

    public Telefone buscarTelefonePeloId(Long id) {
        Telefone telefoneSalvo = telefoneRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));

        return telefoneSalvo;
    }

}
