package com.algaworks.algafoodapi.api.controller.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CozinhaIdInput {
    @NotNull
    private Long id;
}
