package br.com.alura.clientelo.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ITEM_PEDIDO")
public class ItemPedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "preco_unitario")
	private final BigDecimal precoUnitario;
	private final Long quantidade;
	@ManyToOne
	private final Produto produto;
	@ManyToOne
	private Pedido pedido;
	private BigDecimal desconto;
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_desconto")
	private final TipoDescontoItemPedido tipoDesconto;
	@Column(name = "valor_pago")
	private BigDecimal valorPago;

	public ItemPedido(Long quantidade, Produto produto, BigDecimal desconto, TipoDescontoItemPedido tipoDesconto) {
		this.quantidade = quantidade;
		this.produto = produto;
		this.tipoDesconto = tipoDesconto;
		this.precoUnitario = produto.getPreco();
		this.setDesconto(desconto);
	}

	private void calcularValorGasto() {
		BigDecimal valorGastoSemDesconto = (precoUnitario.multiply(new BigDecimal(quantidade)));
		this.valorPago = valorGastoSemDesconto.multiply(new BigDecimal("1").subtract(desconto));
	}

	public Long getId() {
		return id;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public TipoDescontoItemPedido getTipoDesconto() {
		return tipoDesconto;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public void setDesconto(BigDecimal desconto) {
		if (desconto.compareTo(new BigDecimal("0.81")) > 0) {
			throw new RuntimeException("Não é possível ter um desconto acima de 80%");
		}
		this.desconto = desconto;
		calcularValorGasto();
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	@Override
	public String toString() {
		return "ItemPedido{" +
				"id=" + id +
				", precoUnitario=" + precoUnitario +
				", quantidade=" + quantidade +
				", produto=" + produto +
				", pedido=" + pedido.getId() +
				", desconto=" + desconto +
				", tipoDesconto=" + tipoDesconto +
				", valorPago=" + valorPago +
				'}';
	}
}
