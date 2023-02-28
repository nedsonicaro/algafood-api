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
        return cidadeRepository.findAll();
    }

    public Cidade buscar(Long cidadeId) {
        cidadeRepository.findById(cidadeId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de cidade com código %d", cidadeId)));

        return cidadeRepository.findById(cidadeId).get();
    }

    public Cidade salvar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();
        Estado estado = estadoRepository.findById(estadoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format("Não existe um cadastro de estado com código %d", estadoId)));

        cidade.setEstado(estado);
        return cidadeRepository.save(cidade);
    }

    public Cidade atualizar(Long cidadeId, Cidade cidade) throws SQLIntegrityConstraintViolationException {
        Cidade cidadeAtual = cidadeRepository.findById(cidadeId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format("Não existe um cadastro de cidade com código %d", cidadeId)));

        Estado estadoAtual = estadoRepository.findById(cidade.getEstado().getId())
                .orElseThrow(() -> new SQLIntegrityConstraintViolationException());

        cidadeAtual.setNome(cidade.getNome());
        cidadeAtual.setEstado(estadoAtual);
        cidadeRepository.save(cidadeAtual);
        return cidadeAtual;
    }

    public void excluir(Long cidadeId) {
        try {
            cidadeRepository.deleteById(cidadeId);
        }catch (Exception e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de cidade com código %d", cidadeId));
        }
    }
}
