package com.algaworks.algafoodapi;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import com.algaworks.algafoodapi.util.ResourceUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import com.algaworks.algafoodapi.util.DatabaseCleaner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroCozinhaIT {

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    private int quantidadeCozinhasCadastradas;

    private String jsonCozinhaChinesa;

    private Cozinha cozinhaAmericana;
    private Cozinha cozinhaTailandesa;

    @Before
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/cozinhas";

        jsonCozinhaChinesa = ResourceUtils.getContentFromResource("/json/cadastro_cozinha.json");

        databaseCleaner.clearTables();
        prepararDados();
    }

    @Test
    public void deveRetornarStatus200_QuandoConsultarCozinhas() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deveConterQuantidadeDeCozinhasCadastradas_QuandoConsultarCozinhas() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .body("", hasSize(quantidadeCozinhasCadastradas));
    }

    @Test
    public void deveRetornarStatus201_QuandoCadastrarCozinha() {
        given()
                .body(jsonCozinhaChinesa)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }
    @Test
    public void deveRetornarRespostaEStatusCorretos_QuandoConsultarCozinhaExistente() {
        given()
            .pathParams("cozinhaId", cozinhaAmericana.getId())
            .accept(ContentType.JSON)
        .when()
            .get("/{cozinhaId}")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("nome", equalTo(cozinhaAmericana.getNome()));
    }
    @Test
    public void deveRetornarStatus404_QuandoConsultarCozinhaInexistente() {
        given()
            .pathParams("cozinhaId", 100)
            .accept(ContentType.JSON)
        .when()
            .get("/{cozinhaId}")
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }

    private void prepararDados() {
        cozinhaTailandesa = new Cozinha();
        cozinhaTailandesa.setNome("Tailandesa");
        cozinhaRepository.save(cozinhaTailandesa);

        cozinhaAmericana = new Cozinha();
        cozinhaAmericana.setNome("Americana");
        cozinhaRepository.save(cozinhaAmericana);

        quantidadeCozinhasCadastradas = (int) cozinhaRepository.count();

    }

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

