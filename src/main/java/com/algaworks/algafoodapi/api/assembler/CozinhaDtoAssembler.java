package com.algaworks.algafoodapi.api.assembler;

import com.algaworks.algafoodapi.api.DTO.CozinhaDto;
import com.algaworks.algafoodapi.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CozinhaDtoAssembler {
    @Autowired
    private ModelMapper modelMapper;

    public CozinhaDto toDTO(Cozinha cozinha) {
        return modelMapper.map(cozinha, CozinhaDto.class);
    }

    public List<CozinhaDto> toCollectionDTO(List<Cozinha> cozinhas) {
        return cozinhas.stream()
                .map(cozinha -> toDTO(cozinha)).collect(Collectors.toList());
    }
}
