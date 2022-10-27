package br.com.alura.clientelo;

import java.math.BigDecimal;
import java.util.*;

public class CaixaPedidos {
	private final Set<Pedido> pedidos = new HashSet<>();
	private int totalDeProdutosVendidos;
	private int totalDePedidosRealizados;
	private BigDecimal montanteDeVendas = BigDecimal.ZERO;
	private Pedido pedidoMaisBarato;
	private Pedido pedidoMaisCaro;
	private final Set<String> categorias = new HashSet<>();

	public void registrarPedido(Pedido pedido) {
		if (pedido == null) {
			return;
		}
		pedidos.add(pedido);
		totalDePedidosRealizados++;
		totalDeProdutosVendidos+= pedido.getQuantidade();
		atualizaMontanteDeVendas(pedido);
		atualizaPedidoMaisBarato(pedido);
		atualizaPedidoMaisCaro(pedido);
		atualizaTotalCategorias(pedido.getCategoria());
	}


	private void atualizaTotalCategorias(String categoria) {
		if (categoria.isEmpty() || categorias.contains(categoria)) {
			return;
		}
		categorias.add(categoria);
	}

	private void atualizaPedidoMaisCaro(Pedido pedido) {
		if (pedidoMaisCaro == null || pedido.getPreco().multiply(new BigDecimal(pedido.getQuantidade()))
				.compareTo(pedidoMaisCaro.getPreco().multiply(new BigDecimal(pedidoMaisCaro.getQuantidade()))) > 0) {
			pedidoMaisCaro = pedido;
		}
	}

	private void atualizaPedidoMaisBarato(Pedido pedido) {
		if (pedidoMaisBarato == null || pedido.getPreco().multiply(new BigDecimal(pedido.getQuantidade()))
				.compareTo(pedidoMaisBarato.getPreco().multiply(new BigDecimal(pedidoMaisBarato.getQuantidade()))) < 0) {
			pedidoMaisBarato = pedido;
		}
	}



	private void atualizaMontanteDeVendas(Pedido pedido) {
		montanteDeVendas = montanteDeVendas.add(pedido.getPreco().multiply(new BigDecimal(pedido.getQuantidade())));
	}

	public Set<Pedido> getPedidos() {
		return Collections.unmodifiableSet(pedidos);
	}

	public int getTotalDeProdutosVendidos() {
		return totalDeProdutosVendidos;
	}

	public int getTotalDePedidosRealizados() {
		return totalDePedidosRealizados;
	}

	public BigDecimal getMontanteDeVendas() {
		return montanteDeVendas;
	}

	public Pedido getPedidoMaisBarato() {
		return pedidoMaisBarato;
	}

	public Pedido getPedidoMaisCaro() {
		return pedidoMaisCaro;
	}

	public Set<String> getCategorias() {
		return Collections.unmodifiableSet(categorias);
	}
}
