package br.com.alura.clientelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RelatorioProdutosMaisVendidos {
	private static final String TITULO = "PEDIDOS MAIS VENDIDOS:";
	private List<Item> itens;
	private CaixaPedidos caixaPedidos;
	private RelatorioCommon relatorioCommon;
	private int quantidade;

	public RelatorioProdutosMaisVendidos(CaixaPedidos caixaPedidos, int quantidade) {
		this.caixaPedidos=caixaPedidos;
		itens = new ArrayList<>();
		relatorioCommon = new RelatorioCommon();
		this.quantidade = quantidade;
	}

	public String gerarRelatorio() {
		List<Pedido> pedidos = new ArrayList<>(caixaPedidos.getPedidos());

		if ((quantidade < 1 || pedidos.size() < 1) && quantidade <= pedidos.size()) {
			return null;
		}

		ordenarDescrescentePorQuantidade(pedidos);
		mapearResultado(pedidos.subList(0,quantidade));

		return relatorioCommon.formatedItemList(TITULO,itens);
	}

	private void mapearResultado(List<Pedido> pedidos) {
		for (Pedido pedido : pedidos) {
			Item item = new Item(pedido.getProduto(), pedido.getQuantidade());
			itens.add(item);
		}
	}

	private void ordenarDescrescentePorQuantidade(List<Pedido> pedidos) {
		Collections.sort(pedidos, new Comparator<Pedido>() {
			@Override
			public int compare(Pedido pedido, Pedido pedidoComparado) {
				return pedido.getQuantidade() > pedidoComparado.getQuantidade() ? 1 : -1;
			}
		});
		Collections.reverse(pedidos);
	}

	private class Item {
		String produto;
		int quantidade;

		public Item(String produto, int quantidade) {
			this.produto = produto;
			this.quantidade = quantidade;
		}

		@Override
		public String toString() {
			return "PRODUTO: ".concat(produto)
					.concat("\nQUANTIDADE: ").concat(String.valueOf(quantidade));
		}
	}
}
