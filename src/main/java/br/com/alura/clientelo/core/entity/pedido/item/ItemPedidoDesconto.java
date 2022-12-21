package br.com.alura.clientelo.core.entity.pedido.item;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.DecimalMax;
import java.math.BigDecimal;

public class ItemPedidoDesconto {
	@DecimalMax(value = "0.8", message = "Não é possível ter um desconto acima de 80%")
	private BigDecimal desconto = BigDecimal.ZERO;
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_desconto")
	private TipoDescontoItemPedido tipoDesconto = TipoDescontoItemPedido.NENHUM;

	public ItemPedidoDesconto(Long quantidadeItem) {
		if (quantidadeItem > 10) {
			this.tipoDesconto = TipoDescontoItemPedido.QUANTIDADE;
			this.desconto = new BigDecimal("0.1");
		}
	}

	public BigDecimal calcularValorComDesconto(BigDecimal valorSemDesconto) {
		return valorSemDesconto.multiply(new BigDecimal("1").subtract(desconto));
	}

	@Deprecated
	public ItemPedidoDesconto() {
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public TipoDescontoItemPedido getTipoDesconto() {
		return tipoDesconto;
	}
}
