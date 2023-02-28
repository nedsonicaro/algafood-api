package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
}
