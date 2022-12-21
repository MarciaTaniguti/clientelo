package br.com.alura.clientelo.core.usecase.dto;

import java.math.BigDecimal;

public record ExibeProdutoDto(
	String nome,
	BigDecimal preco,
	String descricao,
	Long quantidadeEstoque,
	Long idCategoria,
	String nomeCategoria) {
}
