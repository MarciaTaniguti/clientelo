package br.com.alura.clientelo.dto;

import java.math.BigDecimal;

public record RelatorioProdutosMaisVendidosDTO (
	String nome,
	Long quantidade,
	String descricao,
	BigDecimal preco) {
}
