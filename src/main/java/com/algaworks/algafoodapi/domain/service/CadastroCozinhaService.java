package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class CadastroCozinhaService {
    @Autowired
    private CozinhaRepository cozinhaRepository;

    public List<Cozinha> listar() {
        return cozinhaRepository.listar();
    }

    public Cozinha buscar(Long cozinhaId) {
        Cozinha cozinhaBusca = cozinhaRepository.buscar(cozinhaId);
        if (cozinhaBusca == null) {
            throw new EntidadeNaoEncontradaException("Cozinha não encontrada");
        }
        return cozinhaRepository.buscar(cozinhaId);
    }
    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.salvar(cozinha);
    }

    public Cozinha atualizar(@PathVariable Long id, Cozinha cozinha) {
        Cozinha cozinhaAtual = cozinhaRepository.buscar(id);

        if (cozinhaAtual == null) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de cozinha com código %d", id));
        }
        cozinhaAtual.setNome(cozinha.getNome());
        cozinhaRepository.salvar(cozinhaAtual);
        return cozinhaAtual;
    }

    public void excluir(Long cozinhaId) {
        try {
            cozinhaRepository.remover(cozinhaId);
        }catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Cozinha de código %d não pode ser removida, pois está em uso", cozinhaId));
        }catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de cozinha com código %d", cozinhaId));
        }
    }
}
