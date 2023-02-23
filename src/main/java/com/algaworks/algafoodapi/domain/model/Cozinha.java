package com.algaworks.algafoodapi.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


import javax.persistence.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cozinha {

    //@JsonRootName("gastronomia") // altera o nome do objeto no json, mas não no banco de dados.
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    //@JsonIgnore // não mostra a propriedade no json.
    //@JsonProperty("titulo") // altera o nome da propriedade no json, mas não no banco de dados.
    @Column(nullable = false)
    private String nome;

}