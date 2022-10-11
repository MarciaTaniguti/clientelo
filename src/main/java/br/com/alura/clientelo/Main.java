package br.com.alura.clientelo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    public static final String PEDIDOS_CSV = "pedidos.csv";

    public static void main(String[] args) {
        Pedido[] pedidos = ProcessadorDeCsv.processaArquivo(PEDIDOS_CSV);
        CaixaPedidos caixaPedidos = new CaixaPedidos();

        for (Pedido pedidoAtual : pedidos) {
            caixaPedidos.registrarPedido(pedidoAtual);
        }

        Relatorio relatorioProdutosMaisVendidos = new RelatorioProdutosMaisVendidos(caixaPedidos, 3);
        Relatorio relatorioVendaPorCategoria = new RelatorioVendaPorCategoria(caixaPedidos);
        Relatorio relatorioProdutoMaisCaroPorCategoria = new RelatorioProdutoMaisCaroPorCategoria(caixaPedidos);
        Relatorio relatorioClienteFieis = new RelatorioClienteFieis(caixaPedidos);

        LOGGER.info("##### RELATÓRIO DE VALORES TOTAIS #####");
        LOGGER.info("TOTAL DE PEDIDOS REALIZADOS: {}", caixaPedidos.getTotalDePedidosRealizados());
        LOGGER.info("TOTAL DE PRODUTOS VENDIDOS: {}", caixaPedidos.getTotalDeProdutosVendidos());
        LOGGER.info("TOTAL DE CATEGORIAS: {}", caixaPedidos.getCategorias().size());
        LOGGER.info("MONTANTE DE VENDAS: {}", retornaNumeroFormatado(caixaPedidos.getMontanteDeVendas()));
        LOGGER.info("PEDIDO MAIS BARATO: {} ({})", retornaNumeroFormatado(caixaPedidos.getPedidoMaisBarato().getTotal())
                , caixaPedidos.getPedidoMaisBarato().getProduto());
        LOGGER.info("PEDIDO MAIS CARO: {} ({})\n", retornaNumeroFormatado(caixaPedidos.getPedidoMaisCaro().getTotal()),
                caixaPedidos.getPedidoMaisCaro().getProduto());
        LOGGER.info("PEDIDOS MAIS VENDIDOS: \n{}", relatorioProdutosMaisVendidos.gerarRelatorio());
        LOGGER.info("RELATÓRIO DE VENDAS POR CATEGORIA:\n{}", relatorioVendaPorCategoria.gerarRelatorio());
        LOGGER.info("RELATÓRIO DE PRODUTOS MAIS CAROS DE CADA CATEGORIA\n{}", relatorioProdutoMaisCaroPorCategoria.gerarRelatorio());
        LOGGER.info("RELATÓRIO DE CLIENTES FIÉIS\n{}", relatorioClienteFieis.gerarRelatorio());
        LOGGER.info("### FIM DO RELATÓRIO ###");
    }

    public static String retornaNumeroFormatado(BigDecimal numero) {
        return NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(numero.setScale(2, RoundingMode.HALF_DOWN));
    }

}
