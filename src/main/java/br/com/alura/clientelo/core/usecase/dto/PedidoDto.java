package br.com.alura.clientelo.core.usecase.dto;

import br.com.alura.clientelo.core.entity.pedido.TipoDescontoPedido;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record PedidoDto (
		Long id,
		ClienteDto cliente,
		BigDecimal desconto,
		TipoDescontoPedido tipoDesconto,
		LocalDate data,
		List<ItemPedidoDto> itens
){
}
