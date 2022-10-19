package br.com.alura.clientelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RelatorioProdutosMaisVendidos {
	private static final String TITULO = "PEDIDOS MAIS VENDIDOS:";
	private final List<Item> itens;
	private final CaixaPedidos caixaPedidos;
	private final RelatorioCommon relatorioCommon;
	private final int quantidade;

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
		pedidos.forEach(pedido -> itens.add(new Item(pedido.getProduto(), pedido.getQuantidade())));
	}

	private void ordenarDescrescentePorQuantidade(List<Pedido> pedidos) {
		pedidos.sort((pedido, pedidoComparado) -> pedido.getQuantidade() > pedidoComparado.getQuantidade() ? 1 : -1);
		Collections.reverse(pedidos);
	}

	private static class Item {
		String produto;
		int quantidade;

		public Item(String produto, int quantidade) {
			this.produto = produto;
			this.quantidade = quantidade;
		}

		@Override
		public String toString() {
			return "PRODUTO: " + produto + "\n" +
					"QUANTIDADE: " + quantidade;
		}
	}
}
