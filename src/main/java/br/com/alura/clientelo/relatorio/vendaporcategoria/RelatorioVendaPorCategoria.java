package br.com.alura.clientelo.relatorio.vendaporcategoria;

import br.com.alura.clientelo.CaixaPedidos;
import br.com.alura.clientelo.RelatorioCommon;
import br.com.alura.clientelo.relatorio.Relatorio;
import br.com.alura.clientelo.service.CategoriaService;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class RelatorioVendaPorCategoria implements Relatorio {
	private static final String TITULO = "VENDAS POR CATEGORIA:";
	private final List<Item> itens = new ArrayList<>();
	private final CaixaPedidos caixaPedidos;
	private final RelatorioCommon relatorioCommon = new RelatorioCommon();
	private List<String> categorias;
	private CategoriaService categoriaService = new CategoriaService();


	public RelatorioVendaPorCategoria(CaixaPedidos caixaPedidos) {
		this.caixaPedidos = caixaPedidos;
		Optional<Set<String>> categorias = categoriaService.getCategorias(Optional.of(caixaPedidos.getPedidos()));
		categorias.ifPresentOrElse((categoria) -> this.categorias = new ArrayList<>(categorias.get()),
				() -> this.categorias = new ArrayList<>());

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
