package br.com.alura.clientelo.dto;

import java.math.BigDecimal;

public record ProdutoDto(
	String nome,
	BigDecimal preco,
	String descricao,
	Long quantidadeEstoque,
	CategoriaDto categoria) {
}
