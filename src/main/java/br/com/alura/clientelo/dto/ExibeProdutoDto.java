package br.com.alura.clientelo.dto;

import java.math.BigDecimal;

public record ExibeProdutoDto(
	String nome,
	BigDecimal preco,
	String descricao,
	Long quantidadeEstoque,
	Long idCategoria,
	String nomeCategoria) {
}
