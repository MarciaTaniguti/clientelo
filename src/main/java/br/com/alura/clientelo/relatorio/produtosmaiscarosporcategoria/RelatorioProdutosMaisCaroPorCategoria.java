package br.com.alura.clientelo.relatorio.produtosmaiscarosporcategoria;

import br.com.alura.clientelo.CaixaPedidos;
import br.com.alura.clientelo.RelatorioCommon;
import br.com.alura.clientelo.relatorio.Relatorio;
import br.com.alura.clientelo.service.CategoriaService;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class RelatorioProdutosMaisCaroPorCategoria implements Relatorio {
	private static final String TITULO = "PRODUTOS MAIS CAROS DE CADA CATEGORIA:";
	private static final RelatorioCommon relatorioCommon = new RelatorioCommon();
	private static final CategoriaService categoriaService = new CategoriaService();
	private List<Item> itens;
	private final CaixaPedidos caixaPedidos;
	private List<String> categorias;

	public RelatorioProdutosMaisCaroPorCategoria(CaixaPedidos caixaPedidos) {
		this.caixaPedidos = caixaPedidos;
		Optional<Set<String>> categorias = categoriaService.getCategorias(Optional.of(caixaPedidos.getPedidos()));
		categorias.ifPresentOrElse((categoria) -> this.categorias = new ArrayList<>(categorias.get()),
				() -> this.categorias = new ArrayList<>());
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
