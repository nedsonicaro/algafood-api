package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.DTO.UsuarioDTO;
import com.algaworks.algafoodapi.api.DTO.input.SenhaInput;
import com.algaworks.algafoodapi.api.DTO.input.UsuarioComSenhaInput;
import com.algaworks.algafoodapi.api.DTO.input.UsuarioInput;
import com.algaworks.algafoodapi.api.assembler.UsuarioDtoAssembler;
import com.algaworks.algafoodapi.api.assembler.UsuarioInputDisassembler;
import com.algaworks.algafoodapi.domain.model.Usuario;
import com.algaworks.algafoodapi.domain.repository.UsuarioRepository;
import com.algaworks.algafoodapi.domain.service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CadastroUsuarioService cadastroUsuario;
    @Autowired
    private UsuarioDtoAssembler usuarioDtoAssembler;
    @Autowired
    private UsuarioInputDisassembler usuarioInputDisassembler;
    @GetMapping
    public List<UsuarioDTO> listar() {
        return usuarioDtoAssembler.toCollectionDto(usuarioRepository.findAll());
    }
    @GetMapping("/{usuarioId}")
    public UsuarioDTO buscar(@PathVariable Long usuarioId) {
        Usuario usuario = cadastroUsuario.buscarOuFalhar(usuarioId);
        return usuarioDtoAssembler.toDto(usuario);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDTO adicionar(@RequestBody @Valid UsuarioComSenhaInput usuarioInput) {
        Usuario usuario = usuarioInputDisassembler.toDomainObject(usuarioInput);
        usuario = cadastroUsuario.salvar(usuario);

        return usuarioDtoAssembler.toDto(usuario);
    }
    @PutMapping("/{usuarioId}")
    public UsuarioDTO atualizar(@PathVariable Long usuarioId, @RequestBody @Valid UsuarioInput usuarioInput) {
        Usuario usuarioAtual = cadastroUsuario.buscarOuFalhar(usuarioId);
        usuarioInputDisassembler.copyToDomainObject(usuarioInput, usuarioAtual);
        usuarioAtual = cadastroUsuario.salvar(usuarioAtual);
        return usuarioDtoAssembler.toDto(usuarioAtual);
    }
    @PutMapping("/{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInput senha) {
        cadastroUsuario.alterarSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
    }
}
