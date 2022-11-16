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
	private BigDecimal precoUnitario;
	private Long quantidade;
	@ManyToOne
	private Produto produto;
	private BigDecimal desconto;
	@Column(name = "tipo_desconto")
	@Enumerated(EnumType.STRING)
	private TipoDescontoItemPedido tipoDesconto;

	public ItemPedido(BigDecimal precoUnitario, Long quantidade, Produto produto, BigDecimal desconto, TipoDescontoItemPedido tipoDesconto) {
		this.precoUnitario = precoUnitario;
		this.quantidade = quantidade;
		this.produto = produto;
		this.desconto = desconto;
		this.tipoDesconto = tipoDesconto;
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
}
