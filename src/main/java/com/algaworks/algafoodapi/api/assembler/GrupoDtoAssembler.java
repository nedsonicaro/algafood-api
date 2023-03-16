package com.algaworks.algafoodapi.api.assembler;

import com.algaworks.algafoodapi.api.DTO.GrupoDTO;
import com.algaworks.algafoodapi.domain.model.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GrupoDtoAssembler {
    @Autowired
    private ModelMapper modelMapper;

    public GrupoDTO toDto(Grupo grupo) {
        return modelMapper.map(grupo, GrupoDTO.class);
    }
    public List<GrupoDTO> toCollectionDto(List<Grupo> grupos) {
        return grupos.stream().map(grupo -> toDto(grupo)).collect(Collectors.toList());
    }
}
