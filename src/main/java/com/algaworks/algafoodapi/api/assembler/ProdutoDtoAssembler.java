package com.algaworks.algafoodapi.api.assembler;

import com.algaworks.algafoodapi.api.DTO.ProdutoDto;
import com.algaworks.algafoodapi.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutoDtoAssembler {
    @Autowired
    private ModelMapper modelMapper;

    public ProdutoDto toDto(Produto Produto) {
        return modelMapper.map(Produto, ProdutoDto.class);
    }

    public List<ProdutoDto> toCollectionDto(List<Produto> produtos) {
        return produtos.stream().map(produto -> toDto(produto)).collect(Collectors.toList());
    }
}
