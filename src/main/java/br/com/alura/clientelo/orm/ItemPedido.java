package br.com.alura.clientelo.orm;

import br.com.alura.clientelo.api.mapper.Default;

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
	@ManyToOne(cascade = CascadeType.ALL)
	private Pedido pedido;
	@Embedded
	private ItemPedidoDesconto desconto;
	@Column(name = "valor_pago")
	private BigDecimal valorPago;
	@Transient
	private BigDecimal valorTotal;

	public ItemPedido(Long quantidade, Produto produto) {
		this.quantidade = quantidade;
		this.produto = produto;
		this.precoUnitario = produto.getPreco();
		this.desconto = new ItemPedidoDesconto(quantidade);
		calcularValorTotal();
		aplicarDesconto();
	}

	@Default
	public ItemPedido(Long id, Long quantidade) {
		this.id = id;
		this.quantidade = quantidade;
	}

	@Deprecated
	public ItemPedido() {
	}

	private void calcularValorTotal() {
		this.valorTotal = (precoUnitario.multiply(new BigDecimal(quantidade)));
	}

	private void aplicarDesconto() {
		valorPago = this.desconto.calcularValorComDesconto(valorTotal);
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public BigDecimal getValorDesconto() {
		calcularValorTotal();
		aplicarDesconto();
		return valorTotal.multiply(desconto.getDesconto());
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

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
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
				", desconto=" + desconto.getDesconto() +
				", tipoDesconto=" + desconto.getTipoDesconto() +
				", valorPago=" + valorPago +
				'}';
	}
}
