package br.com.alura.clientelo.api.form;

import com.mysql.cj.log.Log;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public record PedidoForm(
		@NotNull
		Long idCliente,
		@NotEmpty
		List<ItemPedidoForm> itens) {

}
