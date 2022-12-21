package br.com.alura.clientelo.core.usecase.dto;

import java.math.BigDecimal;

public record RelatorioProdutosMaisVendidosDTO (
	String nome,
	Long quantidade,
	BigDecimal preco) {
}
