package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import com.algaworks.algafoodapi.domain.service.CadastroCozinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
    @Autowired
    private CozinhaRepository cozinhaRepository;
    @Autowired
    private CadastroCozinhaService cadastroCozinha;


    @GetMapping
    public List<Cozinha> listar() {
        return cadastroCozinha.listar();
    }
    //@ResponseStatus(HttpStatus.CREATED) Modifica o status da resposta HTTP (No caso para CREATED).
    @GetMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId) {
        try {
            Cozinha cozinha = cadastroCozinha.buscar(cozinhaId);
            return ResponseEntity.ok(cozinha);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Cozinha cozinha) {
        try {
            cadastroCozinha.salvar(cozinha);
            return ResponseEntity.status(HttpStatus.CREATED).body(cozinha);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<?> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha) {
        try {
            cadastroCozinha.atualizar(cozinhaId, cozinha);
            return ResponseEntity.ok(cozinha);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity<?> remover (@PathVariable Long cozinhaId) {
        try {
            cadastroCozinha.excluir(cozinhaId);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();

        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Cozinha de código " + cozinhaId + " não pode ser removida, pois está em uso.");
        }
    }
}
