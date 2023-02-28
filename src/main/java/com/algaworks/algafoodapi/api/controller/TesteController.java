package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teste")
public class TesteController {
    @Autowired
    private CozinhaRepository cozinhaRepository;
    @GetMapping("/cozinhas/por-nome")
    public List<Cozinha> listarPorNome(String nome) {
        return cozinhaRepository.findByNome(nome);
    }
}
