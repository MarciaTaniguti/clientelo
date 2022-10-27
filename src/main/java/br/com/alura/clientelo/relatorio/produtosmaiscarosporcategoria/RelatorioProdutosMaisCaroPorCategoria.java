package br.com.alura.clientelo.relatorio.produtosmaiscarosporcategoria;

import br.com.alura.clientelo.CaixaPedidos;
import br.com.alura.clientelo.RelatorioCommon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class RelatorioProdutosMaisCaroPorCategoria {
	private static final String TITULO = "PRODUTOS MAIS CAROS DE CADA CATEGORIA:";
	private static final RelatorioCommon relatorioCommon = new RelatorioCommon();
	private List<Item> itens;
	private final CaixaPedidos caixaPedidos;
	private final List<String> categorias;

	public RelatorioProdutosMaisCaroPorCategoria(CaixaPedidos caixaPedidos) {
		this.caixaPedidos = caixaPedidos;
		this.categorias = new ArrayList<>(caixaPedidos.getCategorias());
	}

	public List<Item> construirRelatorio() {
		this.itens = new ArrayList<>();

		Collections.sort(categorias);
		categorias.forEach(categoria -> {
			Item item = new Item(categoria);
			caixaPedidos.getPedidos().stream()
					.filter(pedido -> Objects.equals(pedido.getCategoria(), categoria)
							&& pedido.getPreco().compareTo(item.getPreco()) == 1)
					.forEach(pedido -> item.atualizaItem(pedido.getProduto(), pedido.getPreco()));
			itens.add(item);
		});

		return itens;
	}

	public String gerarRelatorio() {
		construirRelatorio();
		return relatorioCommon.formatedItemList(TITULO, itens);
	}
}
