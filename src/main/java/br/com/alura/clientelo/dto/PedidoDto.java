package br.com.alura.clientelo.dto;

import br.com.alura.clientelo.orm.TipoDescontoPedido;

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
