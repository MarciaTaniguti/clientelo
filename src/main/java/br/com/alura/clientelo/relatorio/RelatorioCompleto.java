package br.com.alura.clientelo.relatorio;

import br.com.alura.clientelo.*;
import br.com.alura.clientelo.relatorio.clientesFieis.RelatorioClientesFieis;
import br.com.alura.clientelo.relatorio.produtosmaiscarosporcategoria.RelatorioProdutosMaisCaroPorCategoria;
import br.com.alura.clientelo.relatorio.produtosmaisvendidos.RelatorioProdutosMaisVendidos;
import br.com.alura.clientelo.relatorio.vendaporcategoria.RelatorioVendaPorCategoria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RelatorioCompleto {
	private static final Logger LOGGER = LoggerFactory.getLogger(RelatorioCompleto.class);

	public void exibirRelatorioCompleto(CaixaPedidos caixaPedidos) {
		RelatorioProdutosMaisVendidos produtosMaisVendidos = new RelatorioProdutosMaisVendidos(caixaPedidos);
		RelatorioVendaPorCategoria vendaPorCategoria = new RelatorioVendaPorCategoria(caixaPedidos);
		RelatorioProdutosMaisCaroPorCategoria produtoMaisCaroPorCategoria = new RelatorioProdutosMaisCaroPorCategoria(caixaPedidos);
		RelatorioClientesFieis clienteFieis = new RelatorioClientesFieis(caixaPedidos);
		RelatorioClientesMaisLucrativos clientesMaisLucrativos = new RelatorioClientesMaisLucrativos(caixaPedidos, 2);

		LOGGER.info("##### RELATÓRIO DE VALORES TOTAIS #####");
		LOGGER.info("TOTAL DE PEDIDOS REALIZADOS: {}", caixaPedidos.getTotalDePedidosRealizados());
		LOGGER.info("TOTAL DE PRODUTOS VENDIDOS: {}", caixaPedidos.getTotalDeProdutosVendidos());
		LOGGER.info("TOTAL DE CATEGORIAS: {}", caixaPedidos.getCategorias().size());
		LOGGER.info("MONTANTE DE VENDAS: {}", RelatorioCommon.retornaNumeroFormatado(caixaPedidos.getMontanteDeVendas()));
		LOGGER.info("PEDIDO MAIS BARATO: {} ({})", RelatorioCommon.retornaNumeroFormatado(caixaPedidos.getPedidoMaisBarato().getTotal())
				, caixaPedidos.getPedidoMaisBarato().getProduto());
		LOGGER.info("PEDIDO MAIS CARO: {} ({})\n", RelatorioCommon.retornaNumeroFormatado(caixaPedidos.getPedidoMaisCaro().getTotal()),
				caixaPedidos.getPedidoMaisCaro().getProduto());
		LOGGER.info(produtosMaisVendidos.gerarRelatorio());
		LOGGER.info(vendaPorCategoria.gerarRelatorio());
		LOGGER.info(produtoMaisCaroPorCategoria.gerarRelatorio());
		LOGGER.info(clienteFieis.gerarRelatorio());
		LOGGER.info(clientesMaisLucrativos.gerarRelatorio());
		LOGGER.info("### FIM DO RELATÓRIO ###");
	}
}
