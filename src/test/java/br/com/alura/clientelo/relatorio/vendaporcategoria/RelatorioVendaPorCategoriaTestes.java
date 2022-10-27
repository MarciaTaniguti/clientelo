package br.com.alura.clientelo.relatorio.vendaporcategoria;

import br.com.alura.clientelo.CaixaPedidos;
import br.com.alura.clientelo.Pedido;
import br.com.alura.clientelo.ProcessadorDeCsv;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RelatorioVendaPorCategoriaTestes {
	private static final String NOME_ARQUIVO_PEDIDOS = "pedidos.csv";
	private static final String NOME_ARQUIVO_PEDIDOS_VAZIO = "pedidos_vazio.csv";
	private static CaixaPedidos caixaPedidos;
	private static RelatorioVendaPorCategoria relatorio;
	private List<Pedido> pedidos;

	@BeforeAll
	public static void iniciaListaDePedidos() {
		caixaPedidos = new CaixaPedidos();
	}

	@Test
	public void deveListarAsVendasPorCategoria() {
		pedidos = ProcessadorDeCsv.processaArquivo(NOME_ARQUIVO_PEDIDOS).stream().toList();
		pedidos.forEach(pedido -> caixaPedidos.registrarPedido(pedido));
		relatorio = new RelatorioVendaPorCategoria(caixaPedidos);

		List<Item> relatorioVendaPorCategoriaEsperado = new ArrayList<>();
		relatorioVendaPorCategoriaEsperado.add(new Item("AUTOMOTIVA", 2, new BigDecimal("1987.97")));
		relatorioVendaPorCategoriaEsperado.add(new Item("CELULARES", 11, new BigDecimal("97801.50")));
		relatorioVendaPorCategoriaEsperado.add(new Item("INFORMÁTICA", 9, new BigDecimal("64698.40")));
		relatorioVendaPorCategoriaEsperado.add(new Item("LIVROS", 9, new BigDecimal("1507.64")));
		relatorioVendaPorCategoriaEsperado.add(new Item("MÓVEIS", 4, new BigDecimal("12378.98")));

		List<Item> relatorioDeVendasPorCategoria = this.relatorio.construirRelatorio();

		assertEquals(relatorioVendaPorCategoriaEsperado, relatorioDeVendasPorCategoria);
	}

	@Test
	public void deveNaoListarAsVendasPorCategoriaQuandoListaEstaVazia() {
		pedidos = ProcessadorDeCsv.processaArquivo(NOME_ARQUIVO_PEDIDOS_VAZIO).stream().toList();
		pedidos.forEach(pedido -> caixaPedidos.registrarPedido(pedido));
		relatorio = new RelatorioVendaPorCategoria(caixaPedidos);

		List<Item> relatorioVendaPorCategoriaEsperado = new ArrayList<>();
		List<Item> relatorioDeVendasPorCategoria = relatorio.construirRelatorio();

		assertEquals(relatorioVendaPorCategoriaEsperado, relatorioDeVendasPorCategoria);
	}



}
