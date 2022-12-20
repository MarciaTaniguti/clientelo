package br.com.alura.clientelo.core.entity.pedido;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

public class PedidoDesconto {
	public static final int QNTD_MINIMO_PEDIDO_CLIENTE_DESCONTO = 5;
	@DecimalMin("0")
	@DecimalMax(value = "0.8", message = "Não é possível ter um desconto acima de 80%")
	private BigDecimal desconto;
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_desconto")
	private TipoDescontoPedido tipoDesconto;

	public PedidoDesconto() {
		this.desconto = BigDecimal.ZERO;
		this.tipoDesconto = TipoDescontoPedido.NENHUM;
	}

	public void aplicar(Long qtdPedidos) {
		if (qtdPedidos >= QNTD_MINIMO_PEDIDO_CLIENTE_DESCONTO) {
			tipoDesconto = TipoDescontoPedido.FIDELIDADE;
			desconto = new BigDecimal("0.1");
		}
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public TipoDescontoPedido getTipoDesconto() {
		return tipoDesconto;
	}
}
