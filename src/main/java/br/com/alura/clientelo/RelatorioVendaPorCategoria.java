package br.com.alura.clientelo;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class RelatorioVendaPorCategoria extends Relatorio{
	public RelatorioVendaPorCategoria(CaixaPedidos caixaPedidos) {
		super(caixaPedidos);
	}

	@Override
	public String gerarRelatorio() {
		CaixaPedidos caixaPedidos = super.getCaixaPedidos();

		if (caixaPedidos == null || caixaPedidos.getCategorias() == null || caixaPedidos.getCategorias().size() <1) {
			return null;
		}

		List<String> categorias = new ArrayList<>(super.getCategorias());
		for (String categoria : categorias) {
			Item item = new Item(categoria);
			for (Pedido pedido : caixaPedidos.getPedidos()) {
				if (pedido.getCategoria().equals(categoria)) {
					item.addVenda(pedido.getQuantidade(), pedido.getPreco());
				}
			}
			super.addItem(item);
		}
		return super.formatedItemList();
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
