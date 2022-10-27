package br.com.alura.clientelo.relatorio.produtosmaisvendidos;

import java.util.Objects;

public class Item {
	private final String produto;
	private final int quantidade;


	public Item(String produto, int quantidade) {
		this.produto = produto;
		this.quantidade = quantidade;
	}

	public String getProduto() {
		return produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	@Override
	public String toString() {
		return "PRODUTO: " + produto + "\n" +
				"QUANTIDADE: " + quantidade;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Item item = (Item) o;
		return quantidade == item.quantidade && Objects.equals(produto, item.produto);
	}

	@Override
	public int hashCode() {
		return Objects.hash(produto, quantidade);
	}
}
