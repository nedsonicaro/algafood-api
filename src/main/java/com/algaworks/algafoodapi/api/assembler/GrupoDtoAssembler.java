package com.algaworks.algafoodapi.api.assembler;

import com.algaworks.algafoodapi.api.DTO.GrupoDto;
import com.algaworks.algafoodapi.domain.model.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GrupoDtoAssembler {
    @Autowired
    private ModelMapper modelMapper;

    public GrupoDto toDto(Grupo grupo) {
        return modelMapper.map(grupo, GrupoDto.class);
    }
    public List<GrupoDto> toCollectionDto(Collection<Grupo> grupos) {
        return grupos.stream().map(grupo -> toDto(grupo)).collect(Collectors.toList());
    }
}
