package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.Cidade;
import com.algaworks.algafoodapi.domain.model.Estado;
import com.algaworks.algafoodapi.domain.repository.CidadeRepository;
import com.algaworks.algafoodapi.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public class CadastroCidadeService {
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private EstadoRepository estadoRepository;

    public List<Cidade> listar() {
        return cidadeRepository.listar();
    }

    public Cidade buscar(Long cidadeId) {
        Cidade cidadeBusca = cidadeRepository.buscar(cidadeId);
        if (cidadeBusca == null) {
            throw new EntidadeNaoEncontradaException("Cidade não encontrada");
        }
        return cidadeRepository.buscar(cidadeId);
    }

    public Cidade salvar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();
        Estado estado = estadoRepository.buscar(estadoId);

        if (estado == null) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de estado com código %d", estadoId));
        }
        cidade.setEstado(estado);
        return cidadeRepository.salvar(cidade);
    }

    public Cidade atualizar(Long cidadeId, Cidade cidade) throws SQLIntegrityConstraintViolationException {
        Cidade cidadeAtual = cidadeRepository.buscar(cidadeId);
        Estado estadoAtual = estadoRepository.buscar(cidade.getEstado().getId());

        if (cidadeAtual == null) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de cidade com código %d", cidadeId));
        }
        if (estadoAtual == null) {
            throw new SQLIntegrityConstraintViolationException();
        }
        cidadeAtual.setNome(cidade.getNome());
        cidadeAtual.setEstado(estadoAtual);
        cidadeRepository.salvar(cidadeAtual);
        return cidadeAtual;
    }

    public void excluir(Long cidadeId) {
        try {
            cidadeRepository.remover(cidadeId);
        }catch (Exception e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de cidade com código %d", cidadeId));
        }
    }
}
