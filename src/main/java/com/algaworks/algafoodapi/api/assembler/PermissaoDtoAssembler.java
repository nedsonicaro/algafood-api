package com.algaworks.algafoodapi.api.assembler;

import com.algaworks.algafoodapi.api.DTO.PermissaoDto;
import com.algaworks.algafoodapi.domain.model.Permissao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PermissaoDtoAssembler {
    @Autowired
    private ModelMapper modelMapper;

    public PermissaoDto toDto(Permissao permissao) {
        return modelMapper.map(permissao, PermissaoDto.class);
    }
    public List<PermissaoDto> toCollectionDto(Collection<Permissao> permissoes) {
        return permissoes.stream().map(permissao -> toDto(permissao)).collect(Collectors.toList());
    }
}
