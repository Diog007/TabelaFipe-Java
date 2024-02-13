package br.com.diogo.TabelaFipe.principal;

import br.com.diogo.TabelaFipe.model.MarcasCarros;
import br.com.diogo.TabelaFipe.services.ConsumoApi;
import br.com.diogo.TabelaFipe.services.ConverterDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverterDados conversor = new ConverterDados();
    private final String ENDERECO = "https://parallelum.com.br/fipe/api/v1/";
    private final String MARCA = "/marcas";
    public void exibeMenu(){
        System.out.println("***** OPÇÔES *****\n" + "Carros\n" + "Motos\n" + "Caminhoes\n");
        System.out.println("Digite uma das opcoes para consultar valores:");
        String marcaDoCarro = leitura.nextLine();
        String endereco = marcaDoCarro;
        var json = consumo.obterDados(ENDERECO + marcaDoCarro + MARCA);
        System.out.println(json);
        List<MarcasCarros> marcasCarros = conversor.obterLista(json, MarcasCarros.class);

        marcasCarros.forEach(System.out::println);
    }

}
