package br.com.alura.clientelo.orm;

import br.com.alura.clientelo.service.CrudClienteService;
import br.com.alura.clientelo.service.CrudPedidoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

public class ItemPedidoTestes {

	@Autowired
	private CrudClienteService clienteService;

	@Autowired
	private CrudPedidoService pedidoService;

	@Test
	public void deveCalcularValorGastoComDesconto() {
//		PedidoDesconto desconto = new PedidoDesconto();
//		desconto.aplicar(pedidoService.quantidadePedidosPorCliente());
//
//		Produto produto = new Produto("teste", null, 1, new Categoria("teste"), new BigDecimal("100"));
//		Cliente cliente = new Cliente("Ana", "123", "123456", new Endereco("Rua", "123", null, "bairro", "sao paulo", "sp"));
//		ItemPedido itemPedido = new ItemPedido(1L, produto, new BigDecimal("0.8"), TipoDescontoItemPedido.PROMOCAO);
//		Pedido pedido = new Pedido(cliente, new BigDecimal("0"), TipoDescontoPedido.NENHUM, List.of(itemPedido));
//
//		Assertions.assertEquals(new BigDecimal("20.0"), itemPedido.getValorPago());
	}
}
