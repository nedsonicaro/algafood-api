package domain.repository;

import domain.model.Cozinha;
import domain.model.FormaPagamento;

import java.util.List;

public interface FormaPagamentoRepository {
    List<FormaPagamento> listar();
    FormaPagamento buscar(Long id);
    FormaPagamento salvar(FormaPagamento formaPagamento);
    public void remover(FormaPagamento formaPagamento);
}
