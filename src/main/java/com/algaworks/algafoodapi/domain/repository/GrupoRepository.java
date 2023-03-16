package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public interface GrupoRepository extends JpaRepository<Grupo, Long> {
}
