package br.com.alura.clientelo.core.usecase.dto;

import java.math.BigDecimal;

public record ProdutoDto(
	String nome,
	BigDecimal preco,
	String descricao,
	Long quantidadeEstoque,
	CategoriaDto categoria) {
}
