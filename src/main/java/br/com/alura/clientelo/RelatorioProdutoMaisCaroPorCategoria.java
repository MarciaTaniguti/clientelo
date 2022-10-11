package br.com.alura.clientelo;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class RelatorioProdutoMaisCaroPorCategoria extends Relatorio{

	public RelatorioProdutoMaisCaroPorCategoria(CaixaPedidos caixaPedidos) {
		super(caixaPedidos);
	}

	@Override
	public String gerarRelatorio() {
		CaixaPedidos caixaPedidos = super.getCaixaPedidos();

		if (caixaPedidos.getPedidos().size() < 1) {
			return null;
		}

		List<String> categorias = new ArrayList<>(super.getCategorias());
		for (String categoria : categorias) {
			Item item = new Item(categoria);
			for (Pedido pedido : caixaPedidos.getPedidos()) {
				if (pedido.getCategoria().equals(categoria) && pedido.getPreco().compareTo(item.preco) == 1) {
					item.preco=pedido.getPreco();
					item.produto=pedido.getProduto();
				}
			}
			super.addItem(item);
		}

		return super.formatedItemList();
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
