package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {
}
