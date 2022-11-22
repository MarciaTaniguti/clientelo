package br.com.alura.clientelo.relatorio;

import br.com.alura.clientelo.*;
import br.com.alura.clientelo.relatorio.clientesFieis.RelatorioClientesFieis;
import br.com.alura.clientelo.relatorio.produtosmaiscarosporcategoria.RelatorioProdutosMaisCaroPorCategoria;
import br.com.alura.clientelo.relatorio.produtosmaisvendidos.RelatorioProdutosMaisVendidos;
import br.com.alura.clientelo.relatorio.vendaporcategoria.RelatorioVendaPorCategoria;
import br.com.alura.clientelo.service.CategoriaService;
import br.com.alura.clientelo.service.PedidoService;
import br.com.alura.clientelo.service.RelatorioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class RelatorioCompleto {
	private static final Logger LOGGER = LoggerFactory.getLogger(RelatorioCompleto.class);

	public void exibirRelatorioCompleto(CaixaPedidos caixaPedidos) {
		List<Relatorio> relatorios = new ArrayList<>();
		relatorios.add(new RelatorioProdutosMaisVendidos(caixaPedidos));
		relatorios.add(new RelatorioVendaPorCategoria(caixaPedidos));
		relatorios.add(new RelatorioProdutosMaisCaroPorCategoria(caixaPedidos));
		relatorios.add(new RelatorioClientesFieis(caixaPedidos));
		relatorios.add(new RelatorioClientesMaisLucrativos(caixaPedidos, 2));
		relatorios.add(new RelatorioVendaPorCategoria(caixaPedidos));

		RelatorioService relatorioService = new RelatorioService(relatorios);
		PedidoService pedidoService = new PedidoService();
		CategoriaService categoriaService = new CategoriaService();

		LOGGER.info("##### RELATÓRIO DE VALORES TOTAIS #####");
//		LOGGER.info("TOTAL DE PEDIDOS REALIZADOS: {}", pedidoService.getTotalPedidos(Optional.of(caixaPedidos.getPedidos())));
//		LOGGER.info("TOTAL DE PRODUTOS VENDIDOS: {}", pedidoService.getTotalProdutosVendidos(Optional.of(caixaPedidos.getPedidos())));
//		LOGGER.info("TOTAL DE CATEGORIAS: {}", categoriaService.getTotalCategorias(Optional.of(caixaPedidos.getPedidos())).get());
//		LOGGER.info("MONTANTE DE VENDAS: {}", RelatorioCommon.retornaNumeroFormatado(pedidoService.getMontanteVendas(Optional.of(caixaPedidos.getPedidos()))));
//		Optional<Pedido> pedidoMaisBarato = pedidoService.getPedidoMaisBarato(Optional.of(caixaPedidos.getPedidos()));
//		pedidoMaisBarato.ifPresent(pedido -> LOGGER.info("PEDIDO MAIS BARATO: {} ({})", RelatorioCommon.retornaNumeroFormatado(pedido.getTotal())
//				, pedido.getProduto()));
//		Optional<Pedido> pedidoMaisCaro = pedidoService.getPedidoMaisCaro(Optional.of(caixaPedidos.getPedidos()));
//		pedidoMaisCaro.ifPresent(pedido -> LOGGER.info("PEDIDO MAIS CARO: {} ({})\n", RelatorioCommon.retornaNumeroFormatado(pedido.getTotal()),
//				pedido.getProduto()));  TODO
		LOGGER.info(relatorioService.gerarRelatorios());
		LOGGER.info("### FIM DO RELATÓRIO ###");
	}
}
