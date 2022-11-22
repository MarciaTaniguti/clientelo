package br.com.alura.clientelo.relatorio.clientesFieis;

import br.com.alura.clientelo.orm.Pedido;

public class Item {
	private final String nomeCliente;
	private int qtdPedidos;

	public Item(String nomeCliente) {
		this.nomeCliente = nomeCliente;
		this.qtdPedidos++;
	}

	public void addPedido(Pedido pedido) {
		if (nomeCliente.equals(pedido.getCliente())) {
			this.qtdPedidos++;
		}
	}

	@Override
	public String toString() {
		return "Nº DE PEDIDOS: ".concat(String.valueOf(qtdPedidos))
				.concat("\nNOME: ").concat(nomeCliente);
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public int getQtdPedidos() {
		return qtdPedidos;
	}
}