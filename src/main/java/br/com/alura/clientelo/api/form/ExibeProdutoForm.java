package br.com.alura.clientelo.api.form;

import java.math.BigDecimal;

public record ExibeProdutoForm(
	String nome,
	BigDecimal preco,
	String descricao,
	Long quantidadeEstoque,
	Long idCategoria,
	String nomeCategoria) {
}
