package br.com.diogo.TabelaFipe.principal;

import br.com.diogo.TabelaFipe.model.MarcasCarros;
import br.com.diogo.TabelaFipe.model.Modelos;
import br.com.diogo.TabelaFipe.services.ConsumoApi;
import br.com.diogo.TabelaFipe.services.ConverterDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
        var endereco1 = endereco +"/"+codigoMarca+"/modelos";
        var marcaVeiculo = consumo.obterDados(endereco1);
        var marcasVeiculos = conversor.obterDados(marcaVeiculo, Modelos.class);

        System.out.println(marcasVeiculos);
        marcasVeiculos.modelos().stream()
                .sorted(Comparator.comparing(MarcasCarros::codigo))
                .forEach(System.out::println);


        System.out.println("Digite um trecho do nome do carro a ser buscado: ");
        var nomeDoVeicul = leitura.nextLine();

        List<MarcasCarros> modelosFiltrados = marcasVeiculos.modelos().stream()
                        .filter(m -> m.nome().toLowerCase().contains(nomeDoVeicul.toLowerCase()))
                                .collect(Collectors.toList());

        System.out.println("Modelos Filtrados: ");
        modelosFiltrados.forEach(System.out::println);


        System.out.println("Digite o codigo do modelo para consultar valores: ");
        var codigoModelo = leitura.nextLine();
        var enderecoRef = endereco1;

        endereco1 = enderecoRef + "/" + codigoModelo + "/anos";
        var modelos = consumo.obterDados(endereco1);

        var modelosVeiculos =conversor.obterLista(modelos, MarcasCarros.class);
        modelosVeiculos.forEach(m -> System.out.println("Cod: " + m.codigo() + " Descrição: " + m.nome()));
        System.out.println(modelosVeiculos);



        }


    }

