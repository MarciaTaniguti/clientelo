package br.com.alura.clientelo;

import br.com.alura.clientelo.relatorio.RelatorioCompleto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Pedido> pedidos = ProcessadorDeCsv.processaArquivo("pedidos.csv").stream().toList();
        CaixaPedidos caixaPedidos = new CaixaPedidos();

        for (Pedido pedidoAtual : pedidos) {
            caixaPedidos.registrarPedido(pedidoAtual);
        }

        RelatorioCompleto relatorioCompleto = new RelatorioCompleto();
        relatorioCompleto.exibirRelatorioCompleto(caixaPedidos);
    }




}
