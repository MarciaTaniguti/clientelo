package br.com.alura.clientelo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main {
    public static final String PEDIDOS_CSV = "pedidos.csv";

    public static void main(String[] args) {
        Pedido[] pedidos = ProcessadorDeCsv.processaArquivo(PEDIDOS_CSV);
        CaixaPedidos caixaPedidos = new CaixaPedidos();

        for (Pedido pedidoAtual : pedidos) {
            caixaPedidos.registrarPedido(pedidoAtual);
        }

        Relatorio relatorio = new Relatorio();
        relatorio.gerarRelatorio(caixaPedidos);
    }




}
