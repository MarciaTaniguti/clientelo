package br.com.alura.clientelo;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RelatorioVendaPorCategoria {
	private static final String TITULO = "VENDAS POR CATEGORIA:";
	private List<Item> itens;
	private CaixaPedidos caixaPedidos;
	private RelatorioCommon relatorioCommon;
	private List<String> categorias;

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

		for (String categoria : categorias) {
			Item item = new Item(categoria);
			for (Pedido pedido : caixaPedidos.getPedidos()) {
				if (pedido.getCategoria().equals(categoria)) {
					item.addVenda(pedido.getQuantidade(), pedido.getPreco());
				}
			}
			itens.add(item);
		}
		return relatorioCommon.formatedItemList(TITULO, itens);
	}

	private class Item {
		private String categoria;
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
			return "CATEGORIA: ".concat(categoria)
					.concat("\nQUANTIDADE VENDIDA: ".concat(String.valueOf(qtdVendida)
							.concat("\nMONTANTE: ").concat(NumberFormat.getCurrencyInstance().format(montante)).concat("\n")));
		}
	}
}
