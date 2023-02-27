package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public List<Restaurante> listar() {
        return restauranteRepository.listar();
    }

    public Restaurante buscar(Long restauranteId) {
        Restaurante restauranteBusca = restauranteRepository.buscar(restauranteId);
        if (restauranteBusca == null) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de restaurante com código %d", restauranteId));
        }
        return restauranteRepository.buscar(restauranteId);
    }
    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);

        if (cozinha == null) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe cadastro de cozinha com código %d", cozinhaId));
        }
        restaurante.setCozinha(cozinha);
        return restauranteRepository.salvar(restaurante);
    }

    public Restaurante atualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante) throws SQLIntegrityConstraintViolationException {
        Restaurante restauranteAtual = restauranteRepository.buscar(restauranteId);
        Cozinha cozinhaAtual = cozinhaRepository.buscar(restaurante.getCozinha().getId());

        if (restauranteAtual == null) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de restaurante com código %d", restauranteId));
        }
        if (cozinhaAtual == null) {
            throw new SQLIntegrityConstraintViolationException();
        }
        restauranteAtual.setNome(restaurante.getNome());
        restauranteAtual.setTaxaFrete(restaurante.getTaxaFrete());
        restauranteAtual.setCozinha(restaurante.getCozinha());
        restauranteRepository.salvar(restauranteAtual);
        return restauranteAtual;
    }

    public void excluir(Long restauranteId) {
        try {
            restauranteRepository.remover(restauranteId);
        } catch (Exception e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de restaurante com código %d", restauranteId));
        }
    }
}