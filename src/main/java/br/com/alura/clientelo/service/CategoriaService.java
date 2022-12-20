package br.com.alura.clientelo.service;

import br.com.alura.clientelo.core.entity.pedido.Pedido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class CategoriaService {
	private static final Logger logger = LoggerFactory.getLogger(CategoriaService.class);

	//	TOTAL DE CATEGORIAS: 5
	public Optional<Integer> getTotalCategorias(Optional<Set<Pedido>> pedidos) {
//		if (pedidos.isPresent()) {
//			return Optional.of(pedidos.orElse(new HashSet<>()).stream()
//					.map(pedido -> pedido.getCategoria())
//					.collect(Collectors.toSet()).size());
//		}  TODO
		return Optional.empty();
	}

	public Optional<Set<String>> getCategorias(Optional<Set<Pedido>> pedidos) {
//		if (pedidos.isPresent()) {  TODO
//			return Optional.of(pedidos.get().stream()
//					.map(pedido -> pedido.getCategoria())
//					.collect(Collectors.toSet()));
//		}
		return Optional.empty();
	}
}
