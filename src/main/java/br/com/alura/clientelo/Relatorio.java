package br.com.alura.clientelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Relatorio<T> {
	private List<T> itens;
	private CaixaPedidos caixaPedidos;
	private List<String> categorias;

	public Relatorio(CaixaPedidos caixaPedidos) {
		this.itens = new ArrayList<>();
		this.caixaPedidos = caixaPedidos;
		this.categorias = new ArrayList<>(caixaPedidos.getCategorias());
		ordernarCategoria();
	}

	private void ordernarCategoria() {
		Collections.sort(categorias);
	}

	public abstract String gerarRelatorio();


	public String formatedItemList() {
		if (itens.isEmpty()) {
			return null;
		}
		String result = itens.toString().replaceAll(", ", "\n");
		return result.replaceAll("[\\[\\]\"]", "");
	}

	public CaixaPedidos getCaixaPedidos() {
		return caixaPedidos;
	}

	public void addItem(T item) {
		itens.add(item);
	}

	public List<T> getItens() {
		return itens;
	}

	public List<String> getCategorias() {
		return Collections.unmodifiableList(categorias);
	}
}
