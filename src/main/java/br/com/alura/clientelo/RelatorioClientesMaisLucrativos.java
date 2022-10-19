package br.com.alura.clientelo;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class RelatorioClientesMaisLucrativos {
	private static final String TITULO = "CLIENTES MAIS LUCRATIVOS:";
	private final CaixaPedidos caixaPedidos;
	private final RelatorioCommon relatorioCommon;
	private final int numeroResultados;

	public RelatorioClientesMaisLucrativos(CaixaPedidos caixaPedidos,int numeroResultados) {
		this.caixaPedidos = caixaPedidos;
		this.numeroResultados = numeroResultados;
		relatorioCommon = new RelatorioCommon();
	}

	public String gerarRelatorio() {
		if (numeroResultados < 1 || caixaPedidos.getPedidos().size() < 1) {
			return null;
		}

		List<Item> pedidosPorCliente = caixaPedidos.getPedidos().stream()
				.collect(Collectors.groupingBy(Pedido::getCliente,
						Collector.of(Item::new, Item::add, Item::merge))).values().stream().toList();

		List<Item> top2ClientesMaisLucrativos = pedidosPorCliente.stream()
				.sorted(Comparator.comparing(Item::getMontanteGasto).reversed()).toList()
				.stream().limit(2).sorted(Comparator.comparing(o -> o.nome)).toList();

		return relatorioCommon.formatedItemList(TITULO,top2ClientesMaisLucrativos);
	}

	public static class Item {
		private String nome;
		private Integer quantidadePedidos;
		private BigDecimal montanteGasto;

		public Item() {
			this.montanteGasto = BigDecimal.ZERO;
			this.quantidadePedidos = 0;
		}

		Item merge(Item itemToMerge) {
			this.quantidadePedidos += itemToMerge.quantidadePedidos;
			this.montanteGasto = this.montanteGasto.add(itemToMerge.montanteGasto);
			return this;
		}

		public void add(Pedido pedido) {
			this.nome = pedido.getCliente();
			this.quantidadePedidos++;
			this.montanteGasto = this.montanteGasto.add(pedido.getPreco().multiply(new BigDecimal(pedido.getQuantidade())));
		}

		public String getNome() {
			return nome;
		}

		public Integer getQuantidadePedidos() {
			return quantidadePedidos;
		}

		public BigDecimal getMontanteGasto() {
			return montanteGasto;
		}

		@Override
		public String toString() {
			return "NOME: " + nome +
					"\nNº DE PEDIDOS: " + quantidadePedidos +
					"\nMONTANTE GASTO: " + RelatorioCommon.retornaNumeroFormatado(montanteGasto);
		}
	}
}
