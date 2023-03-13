package com.algaworks.algafoodapi.api.assembler;

import com.algaworks.algafoodapi.api.controller.model.CozinhaDTO;
import com.algaworks.algafoodapi.api.controller.model.RestauranteDTO;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class RestauranteDtoAssembler {
    public RestauranteDTO toDTO(Restaurante restaurante) {
        CozinhaDTO cozinhaModel = new CozinhaDTO();
        cozinhaModel.setId(restaurante.getCozinha().getId());
        cozinhaModel.setNome(restaurante.getCozinha().getNome());

        RestauranteDTO restauranteModel = new RestauranteDTO();
        restauranteModel.setId(restaurante.getId());
        restauranteModel.setNome(restaurante.getNome());
        restauranteModel.setTaxaFrete(restaurante.getTaxaFrete());
        restauranteModel.setCozinha(cozinhaModel);
        return restauranteModel;
    }

    public List<RestauranteDTO> toCollectionDTO(List<Restaurante> restaurantes) {
        return restaurantes.stream()
                .map(restaurante -> toDTO(restaurante)).collect(Collectors.toList());
    }
}
