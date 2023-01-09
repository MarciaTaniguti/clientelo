package br.com.alura.clientelo.infra.api.rest.form;

import br.com.alura.clientelo.infra.api.rest.cliente.ClienteForm;
import br.com.alura.clientelo.infra.api.rest.produto.DetalheProdutoForm;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

public class DetalhePedidoForm {
	private LocalDate dataPedido;
	private BigDecimal valor;
	private BigDecimal descontos;
	private List<DetalheProdutoForm> produtos;
	private ClienteForm cliente;

	public DetalhePedidoForm(LocalDate dataPedid, BigDecimal valor, BigDecimal descontos, List<DetalheProdutoForm> produtos, ClienteForm cliente) {
		this.dataPedido = dataPedid;
		this.valor = valor;
		this.descontos = descontos;
		this.produtos = produtos;
		this.cliente = cliente;
	}

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getDescontos() {
		return descontos.setScale(2, RoundingMode.HALF_EVEN);
	}

	public void setDescontos(BigDecimal descontos) {
		this.descontos = descontos;
	}

	public List<DetalheProdutoForm> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<DetalheProdutoForm> produtos) {
		this.produtos = produtos;
	}

	public ClienteForm getCliente() {
		return cliente;
	}

	public void setCliente(ClienteForm cliente) {
		this.cliente = cliente;
	}
}
