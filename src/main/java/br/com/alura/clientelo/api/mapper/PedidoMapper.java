package br.com.alura.clientelo.api.mapper;

import br.com.alura.clientelo.api.form.DetalhePedidoForm;
import br.com.alura.clientelo.api.form.DetalheProdutoForm;
import br.com.alura.clientelo.api.form.PedidoForm;
import br.com.alura.clientelo.dto.PedidoDto;
import br.com.alura.clientelo.orm.ItemPedido;
import br.com.alura.clientelo.orm.Pedido;
import br.com.alura.clientelo.orm.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;

@Mapper(componentModel="spring")
public interface PedidoMapper {
	@Mapping(source = "idCliente", target = "cliente.id")
	Pedido toModel(PedidoForm pedido);
	@Mapping(source = "desconto.desconto", target = "desconto")
	@Mapping(source = "desconto.tipoDesconto", target = "tipoDesconto")
	PedidoDto toDto(Pedido pedido);
	@Mapping(source = "data", target = "dataPedido")
	@Mapping(source = "valorTotalPago", target = "valor")
	@Mapping(source = "itens", target = "produtos")
	@Mapping(source = "valorDesconto", target = "descontos")
	DetalhePedidoForm toDetalhePedidoForm(Pedido pedido);

	default DetalheProdutoForm itemPedidoToDetalheProdutoForm(ItemPedido itemPedido) {
		if ( itemPedido == null ) {
			return null;
		}

		Long quantidade = itemPedido.getQuantidade();
		BigDecimal precoUnitario = itemPedido.getPrecoUnitario();
		BigDecimal valor = itemPedido.getValorPago();

		Produto produto = itemPedido.getProduto();
		Long id = produto.getId();
		String nome = produto.getNome();
		String categoria = produto.getCategoria().getNome();
		BigDecimal desconto = itemPedido.getValorDesconto();

		return new DetalheProdutoForm(id, nome, categoria, quantidade, precoUnitario, valor, desconto);
	}
}
