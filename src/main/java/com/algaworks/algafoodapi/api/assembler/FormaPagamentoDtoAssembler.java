package com.algaworks.algafoodapi.api.assembler;

import com.algaworks.algafoodapi.api.DTO.FormaPagamentoDTO;
import com.algaworks.algafoodapi.domain.model.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FormaPagamentoDtoAssembler {
    @Autowired
    private ModelMapper modelMapper;

    public FormaPagamentoDTO toDto(FormaPagamento formaPagamento) {
        return modelMapper.map(formaPagamento, FormaPagamentoDTO.class);
    }

    public List<FormaPagamentoDTO> toCollectionDto(Collection<FormaPagamento> formasPagamento) {
        return formasPagamento.stream().map(formaPagamento -> toDto(formaPagamento)).collect(Collectors.toList());
    }
}
