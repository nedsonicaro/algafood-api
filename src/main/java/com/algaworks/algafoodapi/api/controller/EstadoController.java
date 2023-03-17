package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.DTO.EstadoDto;
import com.algaworks.algafoodapi.api.DTO.input.EstadoInput;
import com.algaworks.algafoodapi.api.assembler.EstadoDtoAssembler;
import com.algaworks.algafoodapi.api.assembler.EstadoInputDisassembler;
import com.algaworks.algafoodapi.domain.model.Estado;
import com.algaworks.algafoodapi.domain.repository.EstadoRepository;
import com.algaworks.algafoodapi.domain.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/estados")
public class EstadoController {
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private EstadoService estadoService;
    @Autowired
    private EstadoDtoAssembler estadoDtoAssembler;
    @Autowired
    private EstadoInputDisassembler estadoInputDisassembler;

    @GetMapping
    public List<EstadoDto> listar() {
        return estadoDtoAssembler.toCollectionDTO(estadoRepository.findAll());
    }

    @GetMapping("/{estadoId}")
    public EstadoDto buscar(@PathVariable Long estadoId) {
        Estado estado = estadoService.buscarOuFalhar(estadoId);
        return estadoDtoAssembler.toDTO(estado);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoDto adicionar(@RequestBody @Valid EstadoInput estadoInput) {
        Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);
        return estadoDtoAssembler.toDTO(estadoService.salvar(estado));
    }

    @PutMapping("/{estadoId}")
    public EstadoDto atualizar(@PathVariable Long estadoId,
                               @RequestBody @Valid EstadoInput estado) {
        Estado estadoAtual = estadoService.buscarOuFalhar(estadoId);

        //BeanUtils.copyProperties(estado, estadoAtual, "id");
        estadoInputDisassembler.copyToDomainObject(estado, estadoAtual);
        return estadoDtoAssembler.toDTO(estadoService.salvar(estadoAtual));
    }

    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long estadoId) {
        estadoService.excluir(estadoId);
    }
}
