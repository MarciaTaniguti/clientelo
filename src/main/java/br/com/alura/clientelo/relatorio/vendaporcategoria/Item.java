package br.com.alura.clientelo.relatorio.vendaporcategoria;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Objects;

public class Item {
	private final String categoria;
	private int qtdVendida = 0;
	private BigDecimal montante = BigDecimal.ZERO;

	public Item(String categoria, int qtdVendida, BigDecimal montante) {
		this.categoria = categoria;
		this.qtdVendida = qtdVendida;
		this.montante = montante;
	}

	public Item(String categoria) {
		this.categoria = categoria;
	}

	public void addVenda(int amount, BigDecimal preco) {
		qtdVendida += amount;
		montante = montante.add(preco.multiply(new BigDecimal(amount)));
	}

	public String getCategoria() {
		return categoria;
	}

	public int getQtdVendida() {
		return qtdVendida;
	}

	public BigDecimal getMontante() {
		return montante;
	}

	@Override
	public String toString() {
		return "\nCATEGORIA: " + categoria + "\n" +
				"QUANTIDADE VENDIDA: " + qtdVendida + "\n" +
				"MONTANTE: " + NumberFormat.getCurrencyInstance().format(montante);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Item item = (Item) o;
		return qtdVendida == item.qtdVendida && Objects.equals(categoria, item.categoria) && Objects.equals(montante, item.montante);
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoria, qtdVendida, montante);
	}
}
