package com.algaworks.algafoodapi;

import com.algaworks.algafoodapi.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.service.CadastroCozinhaService;
import com.algaworks.algafoodapi.domain.service.CadastroRestauranteService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CadastroCozinhaIT {


    public void deveRetornarStatus200_QuandoConsultarCozinhas() {
        RestAssured.given()
                .basePath("/cozinhas")
                .port(8080)
                .accept(ContentType.JSON)
            .when()
                .get()
            .then()
                .statusCode(200);
    }
    //@Autowired
//    private CadastroCozinhaService cadastroCozinha;
//    @Autowired
//    private CadastroRestauranteService cadastroRestaurante;
//
//    @Test
//    public void testCadastroCozinhaComSucesso() {
//        // cenário
//        Cozinha novaCozinha = new Cozinha();
//        novaCozinha.setNome("Chinesa");
//        //ação
//        novaCozinha = cadastroCozinha.salvar(novaCozinha);
//        //validação
//        assertThat(novaCozinha).isNotNull();
//        assertThat(novaCozinha.getId()).isNotNull();
//    }
//    @Test(expected = ConstraintViolationException.class)
//    public void testCadastroCozinhaSemNome() {
//        Cozinha novaCozinha = new Cozinha();
//
//        novaCozinha.setNome(null);
//
//        novaCozinha = cadastroCozinha.salvar(novaCozinha);
//    }
//    @Test(expected = EntidadeEmUsoException.class)
//    public void testExcluirCozinhaEmUso() {
//        cadastroCozinha.excluir(1L);
//    }
//    @Test(expected = CozinhaNaoEncontradaException.class)
//    public void testExcluirCozinhaInexistente() {
//        cadastroCozinha.excluir(100L);
//    }
}
