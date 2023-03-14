package com.algaworks.algafoodapi.api.assembler;

import com.algaworks.algafoodapi.api.DTO.EstadoDTO;
import com.algaworks.algafoodapi.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EstadoDtoAssembler {
    @Autowired
    private ModelMapper modelMapper;

    public EstadoDTO toDTO(Object estado) {
        return modelMapper.map(estado, EstadoDTO.class);
    }
    public List<EstadoDTO> toCollectionDTO(List<Estado> estados) {
        return estados.stream().map(estado -> toDTO(estado)).collect(Collectors.toList());
    }
}
