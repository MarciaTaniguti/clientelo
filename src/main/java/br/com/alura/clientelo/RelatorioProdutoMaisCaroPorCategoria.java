package br.com.alura.clientelo;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;

public class RelatorioProdutoMaisCaroPorCategoria {
	private static final String TITULO = "PRODUTOS MAIS CAROS DE CADA CATEGORIA:";
	private List<Item> itens;
	private CaixaPedidos caixaPedidos;
	private List<String> categorias;
	private RelatorioCommon relatorioCommon;

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

		for (String categoria : categorias) {
			Item item = new Item(categoria);
			for (Pedido pedido : caixaPedidos.getPedidos()) {
				if (pedido.getCategoria().equals(categoria) && pedido.getPreco().compareTo(item.preco) == 1) {
					item.preco=pedido.getPreco();
					item.produto=pedido.getProduto();
				}
			}
			itens.add(item);
		}
		return relatorioCommon.formatedItemList(TITULO, itens);
	}

	private class Item {
		private String categoria;
		private String produto = null;
		private BigDecimal preco = BigDecimal.ZERO;

		public Item(String categoria) {
			this.categoria = categoria;
		}

		@Override
		public String toString() {
			return "CATEGORIA: ".concat(categoria)
					.concat("\nPRODUTO: ".concat(produto))
					.concat("\nPREÇO: ").concat(NumberFormat.getCurrencyInstance().format(preco)).concat("\n");
		}
	}
}
