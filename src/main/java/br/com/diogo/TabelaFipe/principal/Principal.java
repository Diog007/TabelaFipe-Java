package br.com.diogo.TabelaFipe.principal;

import br.com.diogo.TabelaFipe.model.MarcasCarros;
import br.com.diogo.TabelaFipe.model.Modelos;
import br.com.diogo.TabelaFipe.services.ConsumoApi;
import br.com.diogo.TabelaFipe.services.ConverterDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverterDados conversor = new ConverterDados();
    private final String ENDERECO = "https://parallelum.com.br/fipe/api/v1/";
    private final String MARCA = "/marcas";
    private final String MODELO = "/modelos";

    public void exibeMenu(){
        System.out.println("***** OPÇÔES *****\n" + "Carros\n" + "Motos\n" + "Caminhoes\n");
        System.out.println("Digite uma das opcoes para consultar valores:");
        //escolher qual tipo de veiculo deseja pegar as marcas
        String marcaDoCarro = leitura.nextLine();
        String endereco = marcaDoCarro;
        endereco = ENDERECO + marcaDoCarro + MARCA;
        //transforma o objeto json em String usando metodo (obterDados)
        var json = consumo.obterDados(endereco);
        System.out.println(json);

        List<MarcasCarros> marcasCarros = conversor.obterLista(json, MarcasCarros.class);
        marcasCarros.forEach(m -> System.out.println("Cod: " + m.codigo() + "  Descrição: " + m.nome()));

        System.out.println("Informe o codigo da marca para consulta: ");
        var codigoMarca = leitura.nextLine();
        endereco = endereco +"/"+codigoMarca+"/modelos";
        var modeloCarros = consumo.obterDados(endereco);
        var modelosCarros = conversor.obterDados(modeloCarros, Modelos.class);
        System.out.println(modelosCarros);
        modelosCarros.modelos().stream()
                .sorted(Comparator.comparing(MarcasCarros::codigo))
                .forEach(System.out::println);

    }

}
