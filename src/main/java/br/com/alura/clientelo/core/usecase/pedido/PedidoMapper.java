package br.com.alura.clientelo.core.usecase.pedido;

import br.com.alura.clientelo.infra.api.rest.form.DetalhePedidoForm;
import br.com.alura.clientelo.infra.api.rest.produto.DetalheProdutoForm;
import br.com.alura.clientelo.infra.api.rest.form.PedidoForm;
import br.com.alura.clientelo.core.usecase.dto.PedidoDto;
import br.com.alura.clientelo.core.entity.pedido.item.ItemPedido;
import br.com.alura.clientelo.core.entity.pedido.Pedido;
import br.com.alura.clientelo.core.entity.produto.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel="spring")
public interface PedidoMapper {
	@Mapping(source = "idCliente", target = "cliente.id")
	Pedido toModel(PedidoForm pedido);
	@Mapping(source = "desconto.desconto", target = "desconto")
	@Mapping(source = "desconto.tipoDesconto", target = "tipoDesconto")
	PedidoDto toDto(Pedido pedido);
	@Mapping(source = "desconto.desconto", target = "desconto")
	@Mapping(source = "desconto.tipoDesconto", target = "tipoDesconto")
	List<PedidoDto> toDto(List<Pedido> pedido);
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
