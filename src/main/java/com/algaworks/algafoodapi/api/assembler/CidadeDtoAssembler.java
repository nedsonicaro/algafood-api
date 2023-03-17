package com.algaworks.algafoodapi.api.assembler;

import com.algaworks.algafoodapi.api.DTO.CidadeDto;
import com.algaworks.algafoodapi.domain.model.Cidade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CidadeDtoAssembler {
    @Autowired
    private ModelMapper modelMapper;

    public CidadeDto toDTO(Cidade cidade) {
        return modelMapper.map(cidade, CidadeDto.class);
    }

    public List<CidadeDto> toCollectionDTO(List<Cidade> cidades) {
        return cidades.stream().map(cidade -> toDTO(cidade)).collect(Collectors.toList());
    }
}
