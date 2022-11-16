package br.com.alura.clientelo.relatorio.vendaporcategoria;

import br.com.alura.clientelo.CaixaPedidos;
import br.com.alura.clientelo.model.Pedido;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RelatorioVendaPorCategoriaTestes {
	private static CaixaPedidos caixaPedidos;
	private static RelatorioVendaPorCategoria relatorio;
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static final List<Pedido> LISTA_VARIOS_PEDIDOS = new ArrayList<>();
	private static final List<Pedido> LISTA_SEM_PEDIDOS = new ArrayList<>();

	@BeforeAll
	public static void init() {
//		LISTA_VARIOS_PEDIDOS.add(new Pedido("INFORMÁTICA", "Notebook Samsung", "ANA",
//				new BigDecimal("3523.00"), 1, LocalDate.parse("01/01/2022", FORMATTER)));
//		LISTA_VARIOS_PEDIDOS.add(new Pedido("MÓVEIS", "Sofá 3 lugares", "ANA",
//				new BigDecimal("2500.00"), 1, LocalDate.parse("05/01/2022", FORMATTER)));
//		LISTA_VARIOS_PEDIDOS.add(new Pedido("LIVROS", "Clean Architecture", "ANA",
//				new BigDecimal("102.90"), 2, LocalDate.parse("08/01/2022", FORMATTER)));
//		LISTA_VARIOS_PEDIDOS.add(new Pedido("MÓVEIS", "Mesa de jantar 6 lugares", "ELI",
//				new BigDecimal("3678.98"), 1, LocalDate.parse("06/01/2022", FORMATTER)));
//		LISTA_VARIOS_PEDIDOS.add(new Pedido("CELULARES", "iPhone 13 Pro", "ANA",
//				new BigDecimal("9176.00"), 6, LocalDate.parse("13/01/2022", FORMATTER)));
//		LISTA_VARIOS_PEDIDOS.add(new Pedido("INFORMÁTICA", "Monitor Dell 27\"\"", "DANI",
//				new BigDecimal("1889.00"), 3, LocalDate.parse("04/01/2022", FORMATTER)));
//		LISTA_VARIOS_PEDIDOS.add(new Pedido("LIVROS", "Implementing Domain-Driven Design", "GABI",
//				new BigDecimal("144.07"), 3, LocalDate.parse("10/01/2022", FORMATTER)));
//		LISTA_VARIOS_PEDIDOS.add(new Pedido("AUTOMOTIVA", "Jogo de pneus", "BIA",
//				new BigDecimal("1276.79"), 1, LocalDate.parse("15/01/2022", FORMATTER)));
//		LISTA_VARIOS_PEDIDOS.add(new Pedido("LIVROS", "Clean Code", "BIA",
//				new BigDecimal("95.17"), 1, LocalDate.parse("09/01/2022", FORMATTER)));
//		LISTA_VARIOS_PEDIDOS.add(new Pedido("CELULARES", "Galaxy S22 Ultra", "DANI",
//				new BigDecimal("8549.10"), 5, LocalDate.parse("14/01/2022", FORMATTER)));
//		LISTA_VARIOS_PEDIDOS.add(new Pedido("INFORMÁTICA", "Macbook Pro 16", "CAIO",
//				new BigDecimal("31752.00"), 1, LocalDate.parse("03/01/2022", FORMATTER)));
//		LISTA_VARIOS_PEDIDOS.add(new Pedido("LIVROS", "Refactoring Improving the Design of Existing Code", "DANI",
//				new BigDecimal("173.90"), 1, LocalDate.parse("12/01/2022", FORMATTER)));
//		LISTA_VARIOS_PEDIDOS.add(new Pedido("MÓVEIS", "Cama queen size", "DANI",
//				new BigDecimal("3100.00"), 2, LocalDate.parse("07/01/2022", FORMATTER)));
//		LISTA_VARIOS_PEDIDOS.add(new Pedido("AUTOMOTIVA", "Central multimidia", "CAIO",
//				new BigDecimal("711.18"), 1, LocalDate.parse("16/01/2022", FORMATTER)));
//		LISTA_VARIOS_PEDIDOS.add(new Pedido("LIVROS", "Building Microservices", "CAIO",
//				new BigDecimal("300.28"), 2, LocalDate.parse("11/01/2022", FORMATTER)));
//		LISTA_VARIOS_PEDIDOS.add(new Pedido("INFORMÁTICA", "Galaxy Tab S8", "BIA",
//				new BigDecimal("5939.10"), 4, LocalDate.parse("02/01/2022", FORMATTER)));
	}

	@BeforeEach
	public void abreCaixaPedidos() {
		caixaPedidos = new CaixaPedidos();
	}

	@Test
	public void deveListarAsVendasPorCategoria() {
		LISTA_VARIOS_PEDIDOS.forEach(pedido -> caixaPedidos.registrarPedido(pedido));
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
		LISTA_SEM_PEDIDOS.forEach(pedido -> caixaPedidos.registrarPedido(pedido));
		relatorio = new RelatorioVendaPorCategoria(caixaPedidos);

		List<Item> relatorioVendaPorCategoriaEsperado = new ArrayList<>();
		List<Item> relatorioDeVendasPorCategoria = relatorio.construirRelatorio();

		assertEquals(relatorioVendaPorCategoriaEsperado, relatorioDeVendasPorCategoria);
	}




}
