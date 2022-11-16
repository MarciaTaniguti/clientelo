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

	public ItemPedido(Long quantidade, Produto produto, Pedido pedido, BigDecimal desconto, TipoDescontoItemPedido tipoDesconto) {
		this.quantidade = quantidade;
		this.produto = produto;
		this.pedido = pedido;
		this.tipoDesconto = tipoDesconto;
		this.precoUnitario = produto.getPreco();
		this.setDesconto(desconto);
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
	}
}
