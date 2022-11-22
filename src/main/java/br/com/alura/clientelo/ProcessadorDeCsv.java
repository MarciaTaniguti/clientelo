package br.com.alura.clientelo;

import br.com.alura.clientelo.orm.Pedido;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ProcessadorDeCsv {

    private static final int CATEGORIA = 0;
    private static final int PRODUTO = 1;
    private static final int PRECO = 2;
    private static final int QUANTIDADE = 3;
    private static final int DATA = 4;
    private static final int CLIENTE = 5;

    public static Collection<Pedido> processaArquivo(String nomeDoArquivo) {

        try {
            URL recursoCSV = ClassLoader.getSystemResource(nomeDoArquivo);
            Path caminhoDoArquivo = Path.of(recursoCSV.toURI());

            Scanner leitorDeLinhas = new Scanner(caminhoDoArquivo);
            List<Pedido> pedidos = new ArrayList<>();

            if (leitorDeLinhas.hasNext()) {
                leitorDeLinhas.nextLine();
                leitorDeLinhas.useDelimiter("\n");

                leitorDeLinhas.forEachRemaining(linha -> {
                    List<String> registro = Arrays.stream(linha.split(",")).toList();  //TODO - refatorar adicionando um collect
                    String categoria = registro.get(CATEGORIA);
                    String produto = registro.get(PRODUTO);
                    BigDecimal preco = new BigDecimal(registro.get(PRECO));
                    int quantidade = Integer.parseInt(registro.get(QUANTIDADE));
                    LocalDate data = LocalDate.parse(registro.get(DATA),DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    String cliente = registro.get(CLIENTE);

                    //pedidos.add(new Pedido(categoria,produto,cliente,preco,quantidade,data));
                });
            }
            return pedidos;
        } catch (URISyntaxException e) {
            throw new RuntimeException(String.format("Arquivo {} não localizado!", nomeDoArquivo));
        } catch (IOException e) {
            throw new RuntimeException("Erro ao abrir Scanner para processar arquivo!");
        }
    }
}
