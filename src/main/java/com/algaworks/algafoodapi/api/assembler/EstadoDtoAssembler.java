package com.algaworks.algafoodapi.api.assembler;

import com.algaworks.algafoodapi.api.DTO.EstadoDto;
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

    public EstadoDto toDTO(Estado estado) {
        return modelMapper.map(estado, EstadoDto.class);
    }
    public List<EstadoDto> toCollectionDTO(List<Estado> estados) {
        return estados.stream().map(estado -> toDTO(estado)).collect(Collectors.toList());
    }
}
