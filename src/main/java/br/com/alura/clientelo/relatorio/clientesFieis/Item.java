package br.com.alura.clientelo.relatorio.clientesFieis;

public class Item {
	private final String nomeCliente;
	private int qtdPedidos;

	public Item(String nomeCliente) {
		this.nomeCliente = nomeCliente;
		this.qtdPedidos++;
	}

	public void addPedido(int qtd) {
		qtdPedidos++;
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