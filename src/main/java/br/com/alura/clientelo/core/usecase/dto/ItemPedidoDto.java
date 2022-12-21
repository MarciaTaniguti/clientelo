package br.com.alura.clientelo.core.usecase.dto;

import br.com.alura.clientelo.core.entity.pedido.TipoDescontoPedido;

import java.math.BigDecimal;

public record ItemPedidoDto(
		ProdutoDto produto,
		BigDecimal precoUnitario,
		Long quantidade,
		BigDecimal desconto,
		TipoDescontoPedido tipoDesconto) {
}
