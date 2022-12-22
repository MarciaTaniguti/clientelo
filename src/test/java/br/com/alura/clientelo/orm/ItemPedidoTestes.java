package br.com.alura.clientelo.orm;

import br.com.alura.clientelo.core.entity.categoria.Categoria;
import br.com.alura.clientelo.core.entity.pedido.item.ItemPedido;
import br.com.alura.clientelo.core.entity.produto.Produto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class ItemPedidoTestes {

	@Test
	public void deveCalcularValorGastoSemDescontoDeQuantidadeQuandoQuantidadeMenorQue10() {
		Produto produto = new Produto("teste", null, 1L, new Categoria("teste"), new BigDecimal("100"));
		ItemPedido itemPedido = new ItemPedido(1L, produto);

		Assertions.assertEquals(new BigDecimal("100"), itemPedido.getValorPago());
	}

	@Test
	public void deveCalcularValorGastoSemDescontoDeQuantidadeQuandoQuantidadeFor10() {
		Produto produto = new Produto("teste", null, 1L, new Categoria("teste"), new BigDecimal("100"));
		ItemPedido itemPedido = new ItemPedido(10L, produto);

		Assertions.assertEquals(new BigDecimal("1000"), itemPedido.getValorPago());
	}

	@Test
	public void deveCalcularValorGastoComDescontoDeQuantidadeQuandoQuantidadeAcimaDe10() {
		Produto produto = new Produto("teste", null, 1L, new Categoria("teste"), new BigDecimal("100"));
		ItemPedido itemPedido = new ItemPedido(11L, produto);

		Assertions.assertEquals(new BigDecimal("990.0"), itemPedido.getValorPago());
	}
}
