package br.com.alura.clientelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RelatorioClienteFieis extends Relatorio{

	public RelatorioClienteFieis(CaixaPedidos caixaPedidos) {
		super(caixaPedidos);
	}

	@Override
	public String gerarRelatorio() {
		List<Item> itemList = new ArrayList<>();

		for (Pedido pedido : super.getCaixaPedidos().getPedidos()) {
			boolean ehNovoCliente = true;
			for (int i=0; i<itemList.size();i++) {
				if (itemList.get(i).nomeCliente.equals(pedido.getCliente())) {
					itemList.get(i).addPedido(pedido.getQuantidade());
					ehNovoCliente = false;
					break;
				}
			}
			if (ehNovoCliente) {
				Item novoItem = new Item(pedido.getCliente());
				itemList.add(novoItem);
			}
		}
		ordenarLista(itemList);

		super.addItem(itemList);
		return super.formatedItemList();
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
