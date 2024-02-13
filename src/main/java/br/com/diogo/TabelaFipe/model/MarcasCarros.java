package br.com.diogo.TabelaFipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MarcasCarros( @JsonAlias("codigo")Integer codigo,
                            @JsonAlias("nome")String nome) {
}
