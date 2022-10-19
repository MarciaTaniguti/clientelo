package br.com.alura.clientelo;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;

public class RelatorioProdutoMaisCaroPorCategoria {
	private static final String TITULO = "PRODUTOS MAIS CAROS DE CADA CATEGORIA:";
	private final List<Item> itens;
	private final CaixaPedidos caixaPedidos;
	private final List<String> categorias;
	private final RelatorioCommon relatorioCommon;

	public RelatorioProdutoMaisCaroPorCategoria(CaixaPedidos caixaPedidos) {
		this.caixaPedidos = caixaPedidos;
		this.categorias = new ArrayList<>(caixaPedidos.getCategorias());
		this.itens = new ArrayList<>();
		this.relatorioCommon = new RelatorioCommon();
	}

	public String gerarRelatorio() {
		if (caixaPedidos.getPedidos().size() < 1) {
			return null;
		}

		Collections.sort(categorias);
		categorias.stream().forEach(categoria -> {
			Item item = new Item(categoria);
			caixaPedidos.getPedidos().stream()
					.filter(pedido -> Objects.equals(pedido.getCategoria(), categoria)
							&& pedido.getPreco().compareTo(item.preco) == 1)
					.forEach(pedido -> item.atualizaItem(pedido.getProduto(), pedido.getPreco()));
			itens.add(item);
		});
		return relatorioCommon.formatedItemList(TITULO, itens);
	}

	private static class Item {
		private final String categoria;
		private String produto = null;
		private BigDecimal preco = BigDecimal.ZERO;

		public Item(String categoria) {
			this.categoria = categoria;
		}

		public void atualizaItem(String produto, BigDecimal preco) {
			this.produto = produto;
			this.preco = preco;
		}

		@Override
		public String toString() {
			return "CATEGORIA: " + categoria + "\n" +
					"PRODUTO: " + produto + "\n" +
					"PREÇO: " + NumberFormat.getCurrencyInstance().format(preco);
		}
	}
}
