package com.algaworks.algafoodapi.api.DTO.input;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class GrupoInput {
    @NotBlank
    private String nome;
}
