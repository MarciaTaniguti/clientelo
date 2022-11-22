package br.com.alura.clientelo;

import br.com.alura.clientelo.orm.Pedido;

import java.util.*;

public class CaixaPedidos {
	private final Set<Pedido> pedidos = new HashSet<>();

	public void registrarPedido(Pedido pedido) {
		if (pedido == null) {
			return;
		}
		pedidos.add(pedido);
	}

	public Set<Pedido> getPedidos() {
		return Collections.unmodifiableSet(pedidos);
	}
}
