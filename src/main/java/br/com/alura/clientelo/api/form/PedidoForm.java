package br.com.alura.clientelo.api.form;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mysql.cj.log.Log;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class PedidoForm {
	@NotNull
	Long idCliente;
	@NotEmpty
	@Valid
	List<ItemPedidoForm> itens;

	public PedidoForm(Long idCliente, List<ItemPedidoForm> itens) {
		this.idCliente = idCliente;
		this.itens = itens;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public List<ItemPedidoForm> getItens() {
		return itens;
	}

	public void replaceItens(List<ItemPedidoForm> itens) {
		this.itens = itens;
	}
}
