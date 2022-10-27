package br.com.alura.clientelo.relatorio.vendaporcategoria;

import br.com.alura.clientelo.CaixaPedidos;
import br.com.alura.clientelo.RelatorioCommon;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class RelatorioVendaPorCategoria {
	private static final String TITULO = "VENDAS POR CATEGORIA:";
	private final List<Item> itens = new ArrayList<>();
	@NonNull
	private final CaixaPedidos caixaPedidos;
	private final RelatorioCommon relatorioCommon = new RelatorioCommon();
	@NonNull
	private final List<String> categorias;

	public RelatorioVendaPorCategoria(CaixaPedidos caixaPedidos) {
		this.caixaPedidos = caixaPedidos;
		this.categorias = new ArrayList<>(caixaPedidos.getCategorias());
	}

	public List<Item> construirRelatorio() {
		Collections.sort(categorias);

		categorias.forEach(categoria -> {
			Item item = new Item(categoria);
			caixaPedidos.getPedidos().stream()
					.filter(pedido -> categoria.equals(pedido.getCategoria()))
					.forEach(pedido -> item.addVenda(pedido.getQuantidade(), pedido.getPreco()));
			itens.add(item);
		});
		return itens;
	}

	public String gerarRelatorio() {
		construirRelatorio();
		return relatorioCommon.formatedItemList(TITULO, itens);
	}

}
