package br.com.alura.clientelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RelatorioClienteFieis {
	private static final String TITULO = "CLIENTES FIÉIS:";
	private List<Item> itens;
	private CaixaPedidos caixaPedidos;
	private RelatorioCommon relatorioCommon;

	public RelatorioClienteFieis(CaixaPedidos caixaPedidos) {
		this.caixaPedidos = caixaPedidos;
		this.relatorioCommon = new RelatorioCommon();
		this.itens = new ArrayList<>();
	}

	public String gerarRelatorio() {
		for (Pedido pedido : caixaPedidos.getPedidos()) {
			boolean ehNovoCliente = true;
			for (Item iten : itens) {
				if (iten.nomeCliente.equals(pedido.getCliente())) {
					iten.addPedido(pedido.getQuantidade());
					ehNovoCliente = false;
					break;
				}
			}
			if (ehNovoCliente) {
				Item novoItem = new Item(pedido.getCliente());
				itens.add(novoItem);
			}
		}
		ordenarLista(itens);

		return relatorioCommon.formatedItemList(TITULO, itens);
	}

	private void ordenarLista(List<Item> itemList) {
		Collections.sort(itemList, new Comparator<Item>() {
			@Override
			public int compare(Item o1, Item o2) {
				int resultComp = o1.qtdPedidos < o2.qtdPedidos ? 1 : ((o1.qtdPedidos == o2.qtdPedidos) ? 0 : -1);

				if (resultComp != 0) {
					return resultComp;
				}

				return o1.nomeCliente.compareTo(o2.nomeCliente);
			}
		});
	}

	private class Item {
		String nomeCliente;
		int qtdPedidos;

		public Item(String nomeCliente) {
			this.nomeCliente = nomeCliente;
			this.qtdPedidos++;
		}

		public void addPedido(int qtd) {
			qtdPedidos++;
		}

		@Override
		public String toString() {
			return "Nº DE PEDIDOS: ".concat(String.valueOf(qtdPedidos))
					.concat("\nNOME: ").concat(nomeCliente);
		}
	}
}
