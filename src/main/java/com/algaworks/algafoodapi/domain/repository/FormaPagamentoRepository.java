package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.FormaPagamento;

import java.util.List;

public interface FormaPagamentoRepository {
    List<FormaPagamento> listar();
    FormaPagamento buscar(Long id);
    FormaPagamento salvar(FormaPagamento formaPagamento);
    FormaPagamento remover(Long id);
}
