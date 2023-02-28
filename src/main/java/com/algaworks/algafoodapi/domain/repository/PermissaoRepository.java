package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
}
