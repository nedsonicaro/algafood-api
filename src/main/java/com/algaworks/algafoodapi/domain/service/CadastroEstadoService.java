package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.Estado;
import com.algaworks.algafoodapi.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroEstadoService {
    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> listar() {
        return estadoRepository.listar();
    }

    public Estado buscar(Long estadoId) {
        Estado estadoBusca = estadoRepository.buscar(estadoId);
        if (estadoBusca == null) {
            throw new EntidadeNaoEncontradaException("Estado não encontrado");
        }
        return estadoRepository.buscar(estadoId);
    }

    public Estado salvar (Estado estado) {
        return estadoRepository.salvar(estado);
    }

    public Estado atualizar(Long estadoId, Estado estado) {
        Estado estadoAtual = estadoRepository.buscar(estadoId);

        if(estadoAtual == null) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de estado com código %d", estadoId));
        }
        estadoAtual.setNome(estado.getNome());
        estadoRepository.salvar(estadoAtual);
        return estadoAtual;
    }

    public void excluir(Long estadoId) {
        try {
            estadoRepository.remover(estadoId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de estado com código %d", estadoId));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Estado de código %d não pode ser removida, pois está em uso", estadoId));
        }
    }
}
