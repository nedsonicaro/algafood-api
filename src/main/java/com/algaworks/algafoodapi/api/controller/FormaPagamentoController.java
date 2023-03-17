package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.DTO.FormaPagamentoDto;
import com.algaworks.algafoodapi.api.DTO.input.FormaPagamentoInput;
import com.algaworks.algafoodapi.api.assembler.FormaPagamentoDtoAssembler;
import com.algaworks.algafoodapi.api.assembler.FormaPagamentoInputDisassembler;
import com.algaworks.algafoodapi.domain.model.FormaPagamento;
import com.algaworks.algafoodapi.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafoodapi.domain.service.FormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {
    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;
    @Autowired
    private FormaPagamentoService formaPagamentoService;
    @Autowired
    private FormaPagamentoDtoAssembler formaPagamentoDtoAssembler;
    @Autowired
    private FormaPagamentoInputDisassembler formaPagamentoInputDisassembler;

    @GetMapping
    public List<FormaPagamentoDto> listar() {
        return formaPagamentoDtoAssembler.toCollectionDto(formaPagamentoRepository.findAll());
    }
    @GetMapping("/{formaPagamentoId}")
    public FormaPagamentoDto buscar(@PathVariable Long formaPagamentoId) {
        FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(formaPagamentoId);
        return formaPagamentoDtoAssembler.toDto(formaPagamento);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamentoDto adicionar(@RequestBody FormaPagamentoInput formaPagamentoInput) {
        FormaPagamento formaPagamento = formaPagamentoInputDisassembler.toDomainObject(formaPagamentoInput);
        return formaPagamentoDtoAssembler.toDto(formaPagamentoService.salvar(formaPagamento));
    }
    @PutMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.OK)
    public FormaPagamentoDto atualizar(@PathVariable Long formaPagamentoId,
                                       @RequestBody FormaPagamentoInput formaPagamentoInput) {
        FormaPagamento formaPagamentoAtual = formaPagamentoService.buscarOuFalhar(formaPagamentoId);
        formaPagamentoInputDisassembler.copyToDomainObject(formaPagamentoInput, formaPagamentoAtual);

        return formaPagamentoDtoAssembler.toDto(formaPagamentoService.salvar(formaPagamentoAtual));
    }
    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long formaPagamentoId) {
        formaPagamentoService.excluir(formaPagamentoId);
    }
}
