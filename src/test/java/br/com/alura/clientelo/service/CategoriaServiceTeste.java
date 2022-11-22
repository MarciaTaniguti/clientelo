package br.com.alura.clientelo.service;

import br.com.alura.clientelo.orm.Pedido;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class CategoriaServiceTeste {
	private static Set<Pedido> pedidos = new HashSet<>();

	@BeforeAll
	static void init() {  //TODO
//		pedidos.add(new Pedido("INFORMATICA", "Teclado", "Ana", new BigDecimal("500.00"), 1, LocalDate.now()));
//		pedidos.add(new Pedido("DECORAÇÃO", "Quadro", "Bia", new BigDecimal("100.00"), 3, LocalDate.now()));
//		pedidos.add(new Pedido("INFORMATICA", "Alexa", "Pedro", new BigDecimal("400.00"), 2, LocalDate.now()));
	}

	@Test
	public void deveRetornarQuantidadeDeCategorias() {
		CategoriaService categoriaService = new CategoriaService();
		Optional<Integer> totalCategorias = categoriaService.getTotalCategorias(Optional.of(pedidos));
		assertEquals(2, totalCategorias.get().intValue());
	}

}
