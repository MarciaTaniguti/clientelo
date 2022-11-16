package br.com.alura.clientelo.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class PedidoTestes {

	@Test
	public void deveCalcularValorGastoComDesconto() {
		Produto produto = new Produto("teste", null, 1, new Categoria("teste"), new BigDecimal("100"));
		Cliente cliente = new Cliente("Ana", "123", "123456", new Endereco("Rua", "123", null, "bairro", "sao paulo", "sp"));
		ItemPedido itemPedido = new ItemPedido(1L, produto, new BigDecimal("0.8"), TipoDescontoItemPedido.PROMOCAO);
		Pedido pedido = new Pedido(cliente, new BigDecimal("0.1"), TipoDescontoPedido.FIDELIDADE, List.of(itemPedido));

		Assertions.assertEquals(new BigDecimal("18.00"), pedido.getTotalGasto());
		System.out.println(pedido);
	}
}
