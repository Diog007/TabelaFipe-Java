package br.com.diogo.TabelaFipe.services;

public interface IConvertDados {
    <T> T obterDados(String json, Class<T> classe);
}
