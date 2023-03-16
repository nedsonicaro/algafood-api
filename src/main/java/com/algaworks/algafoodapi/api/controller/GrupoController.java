package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.DTO.GrupoDTO;
import com.algaworks.algafoodapi.api.DTO.input.GrupoInput;
import com.algaworks.algafoodapi.api.assembler.GrupoDtoAssembler;
import com.algaworks.algafoodapi.api.assembler.GrupoInputDisassembler;
import com.algaworks.algafoodapi.domain.model.Grupo;
import com.algaworks.algafoodapi.domain.repository.GrupoRepository;
import com.algaworks.algafoodapi.domain.service.CadastroGrupoService;
import org.hibernate.validator.constraints.NotEmpty;
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
    private CadastroGrupoService cadastroGrupoService;
    @Autowired
    private GrupoDtoAssembler grupoDtoAssembler;
    @Autowired
    private GrupoInputDisassembler grupoInputDisassembler;

    @GetMapping
    public List<GrupoDTO> listar() {
        return grupoDtoAssembler.toCollectionDto(grupoRepository.findAll());
    }
    @GetMapping("/{grupoId}")
    public GrupoDTO buscar(@PathVariable Long grupoId) {
        Grupo grupo = cadastroGrupoService.buscarOuFalhar(grupoId);
        return grupoDtoAssembler.toDto(grupo);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoDTO adicionar(@RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupo = grupoInputDisassembler.toDomainObject(grupoInput);
        grupo = cadastroGrupoService.salvar(grupo);
        return grupoDtoAssembler.toDto(grupo);
    }
    @PutMapping("/{grupoId}")
    public GrupoDTO atualizar(@PathVariable Long grupoId,
                              @RequestBody @Valid GrupoInput grupoInput) {

        Grupo grupoAtual = cadastroGrupoService.buscarOuFalhar(grupoId);
        grupoInputDisassembler.copyToDomainObject(grupoInput, grupoAtual);

        return grupoDtoAssembler.toDto(cadastroGrupoService.salvar(grupoAtual));
    }
    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long grupoId) {
        cadastroGrupoService.excluir(grupoId);
    }

}
