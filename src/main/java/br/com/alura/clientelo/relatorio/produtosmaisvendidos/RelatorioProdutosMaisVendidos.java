package br.com.alura.clientelo.relatorio.produtosmaisvendidos;

import br.com.alura.clientelo.CaixaPedidos;
import br.com.alura.clientelo.model.Pedido;
import br.com.alura.clientelo.RelatorioCommon;
import br.com.alura.clientelo.relatorio.Relatorio;

import java.util.*;
import java.util.stream.Collectors;

public class RelatorioProdutosMaisVendidos implements Relatorio {
	private static final String TITULO = "PRODUTOS MAIS VENDIDOS:";
	private final List<Item> itens;
	private final CaixaPedidos caixaPedidos;
	private final RelatorioCommon relatorioCommon;
	private int limite = 3;

	public RelatorioProdutosMaisVendidos(CaixaPedidos caixaPedidos) {
		this.caixaPedidos=caixaPedidos;
		itens = new ArrayList<>();
		relatorioCommon = new RelatorioCommon();
	}

	public List<Item> construirRelatorio() {
		List<Pedido> pedidos = new ArrayList<>(caixaPedidos.getPedidos());

		if (limite > pedidos.size()) {
			limite = pedidos.size();
		}

		ordenarDescrescentePorQuantidade(pedidos);
		return itens;
	}

	public String gerarRelatorio() {
		construirRelatorio();
		return relatorioCommon.formatedItemList(TITULO,itens);
	}

	private void mapearResultado(List<Pedido> pedidos) {
		pedidos.forEach(pedido -> itens.add(new Item(pedido.getProduto(), pedido.getQuantidade())));
	}

	private void ordenarDescrescentePorQuantidade(List<Pedido> pedidos) {
		pedidos.stream().collect(Collectors.groupingBy(Pedido::getProduto,
						Collectors.summingInt(Pedido::getQuantidade))).entrySet().stream()
				.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
				.limit(limite).forEach(item -> itens.add(new Item(item.getKey(), item.getValue())));
	}
}
