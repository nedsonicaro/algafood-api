package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.DTO.GrupoDto;
import com.algaworks.algafoodapi.api.DTO.input.GrupoInput;
import com.algaworks.algafoodapi.api.assembler.GrupoDtoAssembler;
import com.algaworks.algafoodapi.api.assembler.GrupoInputDisassembler;
import com.algaworks.algafoodapi.domain.model.Grupo;
import com.algaworks.algafoodapi.domain.repository.GrupoRepository;
import com.algaworks.algafoodapi.domain.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/grupos")
public class GrupoController {
    @Autowired
    private GrupoRepository grupoRepository;
    @Autowired
    private GrupoService grupoService;
    @Autowired
    private GrupoDtoAssembler grupoDtoAssembler;
    @Autowired
    private GrupoInputDisassembler grupoInputDisassembler;

    @GetMapping
    public List<GrupoDto> listar() {
        return grupoDtoAssembler.toCollectionDto(grupoRepository.findAll());
    }
    @GetMapping("/{grupoId}")
    public GrupoDto buscar(@PathVariable Long grupoId) {
        Grupo grupo = grupoService.buscarOuFalhar(grupoId);
        return grupoDtoAssembler.toDto(grupo);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoDto adicionar(@RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupo = grupoInputDisassembler.toDomainObject(grupoInput);
        grupo = grupoService.salvar(grupo);
        return grupoDtoAssembler.toDto(grupo);
    }
    @PutMapping("/{grupoId}")
    public GrupoDto atualizar(@PathVariable Long grupoId,
                              @RequestBody @Valid GrupoInput grupoInput) {

        Grupo grupoAtual = grupoService.buscarOuFalhar(grupoId);
        grupoInputDisassembler.copyToDomainObject(grupoInput, grupoAtual);

        return grupoDtoAssembler.toDto(grupoService.salvar(grupoAtual));
    }
    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long grupoId) {
        grupoService.excluir(grupoId);
    }

}
