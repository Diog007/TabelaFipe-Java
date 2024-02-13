package br.com.diogo.TabelaFipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public record MarcasCarros(String codigo, String nome) {
}

