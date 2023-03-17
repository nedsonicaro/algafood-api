package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.DTO.ProdutoDto;
import com.algaworks.algafoodapi.api.DTO.input.ProdutoInput;
import com.algaworks.algafoodapi.api.assembler.ProdutoDtoAssembler;
import com.algaworks.algafoodapi.api.assembler.ProdutoInputDisassembler;
import com.algaworks.algafoodapi.domain.model.Produto;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.repository.ProdutoRepository;
import com.algaworks.algafoodapi.domain.service.ProdutoService;
import com.algaworks.algafoodapi.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("restaurantes/{restauranteId}/produtos")
public class RestaranteProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private RestauranteService restauranteService;
    @Autowired
    private ProdutoDtoAssembler produtoDtoAssembler;
    @Autowired
    private ProdutoInputDisassembler produtoInputDisassembler;

    @GetMapping
    public List<ProdutoDto> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
        List<Produto> todosOsProdutos = produtoRepository.findByRestaurante(restaurante);

        return produtoDtoAssembler.toCollectionDto(todosOsProdutos);

    }
    @GetMapping("/{produtoId}")
    public ProdutoDto buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        Produto produto = produtoService.buscarOuFalhar(restauranteId, produtoId);
        return produtoDtoAssembler.toDto(produto);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoDto adicionar(@PathVariable Long restauranteId,
                                @RequestBody @Valid ProdutoInput produtoInput) {

        Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
        Produto produto = produtoInputDisassembler.toDomainObject(produtoInput);
        produto.setRestaurante(restaurante);
        produto = produtoService.salvar(produto);

        return produtoDtoAssembler.toDto(produto);
    }
    @PutMapping("/{produtoId}")
    public ProdutoDto atualizar(@PathVariable Long restauranteId,
                                @PathVariable Long produtoId, @RequestBody @Valid ProdutoInput produtoInput) {
        Produto produtoAtual = produtoService.buscarOuFalhar(restauranteId, produtoId);
        produtoInputDisassembler.copyToDomainObject(produtoInput, produtoAtual);
        produtoAtual = produtoService.salvar(produtoAtual);
        return produtoDtoAssembler.toDto(produtoAtual);
    }
}
