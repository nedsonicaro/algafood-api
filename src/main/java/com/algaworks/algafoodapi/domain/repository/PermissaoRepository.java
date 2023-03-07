package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
}
