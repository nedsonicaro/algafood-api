package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.DTO.EstadoDTO;
import com.algaworks.algafoodapi.api.DTO.input.EstadoInput;
import com.algaworks.algafoodapi.api.assembler.EstadoDtoAssembler;
import com.algaworks.algafoodapi.api.assembler.EstadoInputDisassembler;
import com.algaworks.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.Estado;
import com.algaworks.algafoodapi.domain.repository.EstadoRepository;
import com.algaworks.algafoodapi.domain.service.CadastroEstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/estados")
public class EstadoController {
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private CadastroEstadoService cadastroEstado;
    @Autowired
    private EstadoDtoAssembler estadoDtoAssembler;
    @Autowired
    private EstadoInputDisassembler estadoInputDisassembler;

    @GetMapping
    public List<EstadoDTO> listar() {
        return estadoDtoAssembler.toCollectionDTO(estadoRepository.findAll());
    }

    @GetMapping("/{estadoId}")
    public EstadoDTO buscar(@PathVariable Long estadoId) {
        Estado estado = cadastroEstado.buscarOuFalhar(estadoId);
        return estadoDtoAssembler.toDTO(estado);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoDTO adicionar(@RequestBody @Valid EstadoInput estadoInput) {
        Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);
        return estadoDtoAssembler.toDTO(cadastroEstado.salvar(estado));
    }

    @PutMapping("/{estadoId}")
    public EstadoDTO atualizar(@PathVariable Long estadoId,
                            @RequestBody @Valid EstadoInput estado) {
        Estado estadoAtual = cadastroEstado.buscarOuFalhar(estadoId);

        //BeanUtils.copyProperties(estado, estadoAtual, "id");
        estadoInputDisassembler.copyToDomainObject(estado, estadoAtual);
        return estadoDtoAssembler.toDTO(cadastroEstado.salvar(estadoAtual));
    }

    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long estadoId) {
        cadastroEstado.excluir(estadoId);
    }
}
