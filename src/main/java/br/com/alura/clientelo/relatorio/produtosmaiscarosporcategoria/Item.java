package br.com.alura.clientelo.relatorio.produtosmaiscarosporcategoria;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Objects;

public class Item {
	private final String categoria;
	private String produto = null;
	private BigDecimal preco = BigDecimal.ZERO;

	public Item(String categoria, String produto, BigDecimal preco) {
		this.categoria = categoria;
		this.produto = produto;
		this.preco = preco;
	}

	public Item(String categoria) {
		this.categoria = categoria;
	}

	public void atualizaItem(String produto, BigDecimal preco) {
		this.produto = produto;
		this.preco = preco;
	}

	public String getCategoria() {
		return categoria;
	}

	public String getProduto() {
		return produto;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	@Override
	public String toString() {
		return "CATEGORIA: " + categoria + "\n" +
				"PRODUTO: " + produto + "\n" +
				"PREÇO: " + NumberFormat.getCurrencyInstance().format(preco);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Item item = (Item) o;
		return Objects.equals(categoria, item.categoria) && Objects.equals(produto, item.produto) && Objects.equals(preco, item.preco);
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoria, produto, preco);
	}
}
