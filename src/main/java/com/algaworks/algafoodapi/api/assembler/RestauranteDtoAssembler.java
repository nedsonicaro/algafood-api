package com.algaworks.algafoodapi.api.assembler;

import com.algaworks.algafoodapi.api.DTO.RestauranteDTO;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class RestauranteDtoAssembler {
    @Autowired
    private ModelMapper modelMapper;
    public RestauranteDTO toDTO(Restaurante restaurante) {
        return modelMapper.map(restaurante, RestauranteDTO.class);
    }

    public List<RestauranteDTO> toCollectionDTO(List<Restaurante> restaurantes) {
        return restaurantes.stream()
                .map(restaurante -> toDTO(restaurante)).collect(Collectors.toList());
    }
}
