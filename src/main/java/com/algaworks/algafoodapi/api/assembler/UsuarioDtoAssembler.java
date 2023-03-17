package com.algaworks.algafoodapi.api.assembler;

import com.algaworks.algafoodapi.api.DTO.UsuarioDto;
import com.algaworks.algafoodapi.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioDtoAssembler {
    @Autowired
    private ModelMapper modelMapper;

    public UsuarioDto toDto(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioDto.class);
    }
    public List<UsuarioDto> toCollectionDto(Collection<Usuario> usuarios) {
        return usuarios.stream().map(usuario -> toDto(usuario)).collect(Collectors.toList());
    }
}
