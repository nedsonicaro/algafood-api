package com.algaworks.algafoodapi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public CozinhaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public CozinhaNaoEncontradaException(Long cozinhaId) {
        this(String.format("Não existe um cadastro de cozinha com código %d", cozinhaId));
    }
}

