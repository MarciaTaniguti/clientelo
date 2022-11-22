package br.com.alura.clientelo.service;

import br.com.alura.clientelo.orm.Pedido;

import java.util.*;


public class PedidoService {
	public Integer getTotalPedidos(Optional<Set<Pedido>> pedidos) {
		return pedidos.map(Set::size).orElse(0);
	}
//  TODO
//	public Integer getTotalProdutosVendidos(Optional<Set<Pedido>> pedidos) {
//		return pedidos.map(pedidoSet -> pedidoSet.stream()
//				.map(Pedido::getQuantidade)
//				.reduce(0, Integer::sum)).orElse(0);
//	}
//
//	public BigDecimal getMontanteVendas(Optional<Set<Pedido>> pedidos) {
//		return pedidos.map(pedidoSet -> pedidoSet.stream()
//				.map(pedido -> pedido.getPreco().multiply(new BigDecimal(pedido.getQuantidade())))
//				.reduce(BigDecimal.ZERO, BigDecimal::add)).orElse(BigDecimal.ZERO);
//	}
//
//	public Optional<Pedido> getPedidoMaisBarato(Optional<Set<Pedido>> pedidos) {
//		if (pedidos.isPresent()) {
//			List<Pedido> pedidosList = new ArrayList<>(pedidos.get());
//			pedidosList.sort(Comparator.comparing(Pedido::getTotal));
//			return Optional.of(pedidosList.get(0));
//		}
//		return Optional.empty();
//	}
//
//	public Optional<Pedido> getPedidoMaisCaro(Optional<Set<Pedido>> pedidos) {
//		if (pedidos.isPresent()) {
//			List<Pedido> pedidosList = new ArrayList<>(pedidos.get());
//			pedidosList.sort(Comparator.comparing(Pedido::getTotal));
//			return Optional.of(pedidosList.get(pedidosList.size()-1));
//		}
//		return Optional.empty();
//	}


}
