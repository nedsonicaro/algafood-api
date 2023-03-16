package com.algaworks.algafoodapi.api.assembler;

import com.algaworks.algafoodapi.api.DTO.UsuarioDTO;
import com.algaworks.algafoodapi.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioDtoAssembler {
    @Autowired
    private ModelMapper modelMapper;

    public UsuarioDTO toDto(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioDTO.class);
    }
    public List<UsuarioDTO> toCollectionDto(List<Usuario> usuarios) {
        return usuarios.stream().map(usuario -> toDto(usuario)).collect(Collectors.toList());
    }
}
