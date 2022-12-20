package br.com.alura.clientelo.service;

import br.com.alura.clientelo.core.entity.pedido.Pedido;
import org.junit.jupiter.api.BeforeAll;

import java.util.*;

public class PedidoServiceTeste {

	private static Set<Pedido> pedidos = new HashSet<>();
	private PedidoService pedidoService = new PedidoService();

	@BeforeAll
	static void init() {
//		pedidos.add(new Pedido("INFORMÁTICA", "Teclado", "Ana", new BigDecimal("500.00"), 1, LocalDate.now()));
//		pedidos.add(new Pedido("DECORAÇÃO", "Quadro", "Bia", new BigDecimal("100.00"), 3, LocalDate.now()));
//		pedidos.add(new Pedido("INFORMÁTICA", "Alexa", "Pedro", new BigDecimal("400.00"), 2, LocalDate.now()));
//		pedidos.add(new Pedido("LIVRO", "Clean Architecture", "Dani", new BigDecimal("102.90"), 1, LocalDate.now()));
//		pedidos.add(new Pedido("ELETRODOMÉSTICO", "Cafeteira", "João", new BigDecimal("139.99"), 1, LocalDate.now()));
	}

//	@Test
//	public void deveExibirTotalPedidosRealizados() {
//		int totalPedidos = pedidoService.getTotalPedidos(Optional.of(pedidos));
//		assertEquals(5, totalPedidos);
//	}
//
//	@Test
//	public void deveExibirTotalPedidosVendidos() {
//		int totalPedidosVendidos = pedidoService.getTotalProdutosVendidos(Optional.of(pedidos));
//		assertEquals(8, totalPedidosVendidos);
//	}
//
//	@Test
//	public void deveExibirMontanteVendas() {  //TODO - verificar se tah correto
//		BigDecimal montanteVendas = pedidoService.getMontanteVendas(Optional.of(pedidos));
//		assertEquals(new BigDecimal("1842.89"), montanteVendas);
//	}
//
//	@Test
//	public void deveRetornarPedidoMaisBarato() {
//		Optional<Pedido> pedidoMaisBarato = pedidoService.getPedidoMaisBarato(Optional.of(pedidos));
//		assertEquals(new BigDecimal("102.90"), pedidoMaisBarato.get().getPreco());
//	}
//
//	@Test
//	public void deveRetornarPedidoMaisCaro() {
//		Optional<Pedido> pedidoMaisCaro = pedidoService.getPedidoMaisCaro(Optional.of(pedidos));
//		assertEquals(new BigDecimal("400.00"), pedidoMaisCaro.get().getPreco());
//	}
}
