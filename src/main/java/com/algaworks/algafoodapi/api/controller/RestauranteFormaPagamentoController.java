package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.DTO.FormaPagamentoDTO;
import com.algaworks.algafoodapi.api.assembler.FormaPagamentoDtoAssembler;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormaPagamentoController {
    @Autowired
    private CadastroRestauranteService cadastroRestaurante;
    @Autowired
    private FormaPagamentoDtoAssembler formaPagamentoDtoAssembler;
    @GetMapping
    public List<FormaPagamentoDTO> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);

        return formaPagamentoDtoAssembler.toCollectionDto(restaurante.getFormasPagamento());
    }
    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
        cadastroRestaurante.desassociarFormaPagamento(restauranteId, formaPagamentoId);
    }
    @PutMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
        cadastroRestaurante.associarFormaPagamento(restauranteId, formaPagamentoId);
    }
}
