package br.com.alura.clientelo;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class RelatorioVendaPorCategoria {
	private static final String TITULO = "VENDAS POR CATEGORIA:";
	private final List<Item> itens;
	private final CaixaPedidos caixaPedidos;
	private final RelatorioCommon relatorioCommon;
	private final List<String> categorias;

	public RelatorioVendaPorCategoria(CaixaPedidos caixaPedidos) {
		this.caixaPedidos = caixaPedidos;
		this.categorias = new ArrayList<>(caixaPedidos.getCategorias());
		this.itens = new ArrayList<>();
		this.relatorioCommon = new RelatorioCommon();
	}

	public String gerarRelatorio() {
		if (caixaPedidos == null || caixaPedidos.getCategorias() == null || caixaPedidos.getCategorias().size() <1) {
			return null;
		}

		Collections.sort(categorias);

		categorias.forEach(categoria -> {
			Item item = new Item(categoria);
			caixaPedidos.getPedidos().stream()
					.filter(pedido -> categoria.equals(pedido.getCategoria()))
					.forEach(pedido -> item.addVenda(pedido.getQuantidade(), pedido.getPreco()));
			itens.add(item);
		});

		return relatorioCommon.formatedItemList(TITULO, itens);
	}

	private static class Item {
		private final String categoria;
		private int qtdVendida = 0;
		private BigDecimal montante = BigDecimal.ZERO;

		public Item(String categoria) {
			this.categoria = categoria;
		}

		public void addVenda(int amount, BigDecimal preco) {
			qtdVendida+=amount;
			montante = montante.add(preco.multiply(new BigDecimal(amount)));
		}

		@Override
		public String toString() {
			return "CATEGORIA: " + categoria + "\n" +
					"QUANTIDADE VENDIDA: " + qtdVendida + "\n" +
					"MONTANTE: " + NumberFormat.getCurrencyInstance().format(montante);
		}
	}
}
