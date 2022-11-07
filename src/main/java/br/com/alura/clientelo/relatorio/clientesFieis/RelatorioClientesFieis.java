package br.com.alura.clientelo.relatorio.clientesFieis;

import br.com.alura.clientelo.CaixaPedidos;
import br.com.alura.clientelo.model.Pedido;
import br.com.alura.clientelo.RelatorioCommon;
import br.com.alura.clientelo.relatorio.Relatorio;

import java.util.ArrayList;
import java.util.List;

public class RelatorioClientesFieis implements Relatorio {
	private static final String TITULO = "CLIENTES FIÉIS:";
	private final List<Item> itens;
	private final CaixaPedidos caixaPedidos;
	private final RelatorioCommon relatorioCommon;

	public RelatorioClientesFieis(CaixaPedidos caixaPedidos) {
		this.caixaPedidos = caixaPedidos;
		this.relatorioCommon = new RelatorioCommon();
		this.itens = new ArrayList<>();
	}

	public String gerarRelatorio() {
		caixaPedidos.getPedidos().stream().forEach(pedido -> itens.stream().forEach(item -> item.addPedido(pedido)));

		for (Pedido pedido : caixaPedidos.getPedidos()) {
			boolean ehNovoCliente = true;
			for (Item item : itens) {
				if (item.getNomeCliente().equals(pedido.getCliente())) {
					item.addPedido(pedido);
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

	private void ordenarLista(List<Item> itemList) {  //TODO - refactor
		itemList.sort((o1, o2) -> {
			int resultComp = Integer.compare(o2.getQtdPedidos(), o1.getQtdPedidos());

			if (resultComp != 0) {
				return resultComp;
			}
			return o1.getNomeCliente().compareTo(o2.getNomeCliente());
		});
	}
}
