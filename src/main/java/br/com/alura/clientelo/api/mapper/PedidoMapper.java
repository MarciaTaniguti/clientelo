package br.com.alura.clientelo.api.mapper;

import br.com.alura.clientelo.api.form.PedidoForm;
import br.com.alura.clientelo.orm.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface PedidoMapper {
	@Mapping(source = "idCliente", target = "cliente.id")
	Pedido toModel(PedidoForm pedido);
	@Mapping(source = "cliente.id", target = "idCliente")
	PedidoForm toDto(Pedido pedido);

}
