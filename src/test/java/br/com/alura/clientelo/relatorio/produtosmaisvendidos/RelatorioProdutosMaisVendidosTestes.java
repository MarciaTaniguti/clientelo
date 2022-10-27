package br.com.alura.clientelo.relatorio.produtosmaisvendidos;

import br.com.alura.clientelo.CaixaPedidos;
import br.com.alura.clientelo.Pedido;
import br.com.alura.clientelo.ProcessadorDeCsv;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RelatorioProdutosMaisVendidosTestes {
	private static final String NOME_ARQUIVO_PEDIDOS = "pedidos.csv";
	private static CaixaPedidos caixaPedidos;
	private static RelatorioProdutosMaisVendidos relatorio;

	@BeforeAll
	public static void iniciaListaDePedidos() {
		caixaPedidos = new CaixaPedidos();
	}

	@Test
	public void deveListarProdutosMaisVendidos() {
		List<Pedido> pedidos = ProcessadorDeCsv.processaArquivo(NOME_ARQUIVO_PEDIDOS).stream().toList();
		pedidos.forEach(pedido -> caixaPedidos.registrarPedido(pedido));
		relatorio = new RelatorioProdutosMaisVendidos(caixaPedidos);

		List<Item> relatorioProdutosMaisVendidosEsperado = new ArrayList<>();
		relatorioProdutosMaisVendidosEsperado.add(new Item("iPhone 13 Pro", 6));
		relatorioProdutosMaisVendidosEsperado.add(new Item("Galaxy S22 Ultra", 5));
		relatorioProdutosMaisVendidosEsperado.add(new Item("Galaxy Tab S8", 4));

		List<Item> relatorioProdutosMaisVendidos = relatorio.construirRelatorio();

		Assertions.assertEquals(relatorioProdutosMaisVendidosEsperado, relatorioProdutosMaisVendidos);
	}

	@Test
	public void deveListarProdutoQuandoHouverApenasUmPedido() {
		CaixaPedidos caixaPedidos = new CaixaPedidos();
		caixaPedidos.registrarPedido(new Pedido("INFORMÁTICA", "iPhone 13 Pro", "ANA",
				new BigDecimal("9176.00"), 1, LocalDate.now()));
		relatorio = new RelatorioProdutosMaisVendidos(caixaPedidos);

		List<Item> listaPedidosRelatorioEsperado = new ArrayList<>();
		listaPedidosRelatorioEsperado.add(new Item("iPhone 13 Pro", 1));

		List<Item> listaPedidosRelatorio = relatorio.construirRelatorio();
		Assertions.assertEquals(listaPedidosRelatorioEsperado, listaPedidosRelatorio);
	}
}
