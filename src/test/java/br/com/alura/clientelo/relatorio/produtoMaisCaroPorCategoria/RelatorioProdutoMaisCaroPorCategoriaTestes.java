package br.com.alura.clientelo.relatorio.produtoMaisCaroPorCategoria;

import br.com.alura.clientelo.CaixaPedidos;
import br.com.alura.clientelo.Pedido;
import br.com.alura.clientelo.ProcessadorDeCsv;
import br.com.alura.clientelo.relatorio.produtosmaiscarosporcategoria.Item;
import br.com.alura.clientelo.relatorio.produtosmaiscarosporcategoria.RelatorioProdutosMaisCaroPorCategoria;
import br.com.alura.clientelo.relatorio.produtosmaisvendidos.RelatorioProdutosMaisVendidos;
import br.com.alura.clientelo.relatorio.vendaporcategoria.RelatorioVendaPorCategoria;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RelatorioProdutoMaisCaroPorCategoriaTestes {
	private static final String NOME_ARQUIVO_PEDIDOS = "pedidos.csv";
	private static final String NOME_ARQUIVO_PEDIDOS_VAZIO = "pedidos_vazio.csv";
	private CaixaPedidos caixaPedidos = new CaixaPedidos();
	private static RelatorioProdutosMaisCaroPorCategoria relatorio;
	private static List<Pedido> pedidos;

	@BeforeAll
	public static void init() {
		//TODO - refactor, fazer a logica aqui e nos demais testes de mockar o inpu
	}

	@Test
	public void deveListarProdutosMaisCarosPorCategoria() {
		pedidos = ProcessadorDeCsv.processaArquivo(NOME_ARQUIVO_PEDIDOS).stream().toList();
		pedidos.forEach(pedido -> caixaPedidos.registrarPedido(pedido));
		relatorio = new RelatorioProdutosMaisCaroPorCategoria(caixaPedidos);

		List<Item> produtosMaisCarosPorCategoriaEsperado = new ArrayList<>();
		produtosMaisCarosPorCategoriaEsperado.add(new Item("AUTOMOTIVA","Jogo de pneus",new BigDecimal("1276.79")));
		produtosMaisCarosPorCategoriaEsperado.add(new Item("CELULARES", "iPhone 13 Pro",new BigDecimal("9176.00")));
		produtosMaisCarosPorCategoriaEsperado.add(new Item("INFORMÁTICA", "Macbook Pro 16", new BigDecimal("31752.00")));
		produtosMaisCarosPorCategoriaEsperado.add(new Item("LIVROS","Building Microservices",new BigDecimal("300.28")));
		produtosMaisCarosPorCategoriaEsperado.add(new Item("MÓVEIS","Mesa de jantar 6 lugares",new BigDecimal("3678.98")));

		List<Item> produtosMaisCarosPorCategoria = relatorio.construirRelatorio();
		Assertions.assertEquals(produtosMaisCarosPorCategoriaEsperado, produtosMaisCarosPorCategoria);
	}

	@Test
	public void deveListarProdutoQuandoHouverApenasUmPedido() {
		caixaPedidos = new CaixaPedidos();  //TODO - repensar essa estrategia
		caixaPedidos.registrarPedido(new Pedido("INFORMÁTICA", "iPhone 13 Pro", "ANA",
				new BigDecimal("9176.00"), 1, LocalDate.now()));
		relatorio = new RelatorioProdutosMaisCaroPorCategoria(caixaPedidos);

		List<Item> produtoMaisCaroPorCategoriaEsperado = new ArrayList<>();
		produtoMaisCaroPorCategoriaEsperado
				.add(new Item("INFORMÁTICA", "iPhone 13 Pro", new BigDecimal("9176.00")));

		List<Item> produtoMaisCaroPorCategoria = relatorio.construirRelatorio();
		Assertions.assertEquals(produtoMaisCaroPorCategoriaEsperado, produtoMaisCaroPorCategoria);
	}

	@Test
	public void deveNaoListarProdutoMaisCaroPorCategoria() {
		caixaPedidos = new CaixaPedidos();
		pedidos = ProcessadorDeCsv.processaArquivo(NOME_ARQUIVO_PEDIDOS_VAZIO).stream().toList();
		pedidos.forEach(pedido -> caixaPedidos.registrarPedido(pedido));
		relatorio = new RelatorioProdutosMaisCaroPorCategoria(caixaPedidos);

		List<Item> produtoMaisCariPorCategoriaEsperado = new ArrayList<>();
		List<Item> produtoMaisCaroPorCategoria = relatorio.construirRelatorio();

		assertEquals(produtoMaisCariPorCategoriaEsperado, produtoMaisCaroPorCategoria);
	}
}
