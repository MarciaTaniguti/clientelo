package br.com.alura.clientelo.dto;

import br.com.alura.clientelo.orm.TipoDescontoPedido;

import java.math.BigDecimal;

public record ItemPedidoDto(
		ProdutoDto produto,
		BigDecimal precoUnitario,
		Long quantidade,
		BigDecimal desconto,
		TipoDescontoPedido tipoDesconto) {
}
