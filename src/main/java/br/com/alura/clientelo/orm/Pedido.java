package br.com.alura.clientelo.orm;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PEDIDO")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data;
    @ManyToOne
    private Cliente cliente;
    @Embedded
    private PedidoDesconto desconto = new PedidoDesconto();
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.PERSIST)
    private List<ItemPedido> itens = new ArrayList<>();
    @Column(name = "total_gasto")
    private BigDecimal valorTotalPago;

    @Transient
    private BigDecimal valorTotalCompra;

    @Deprecated
    public Pedido() {
    }

    public Pedido(Cliente cliente, List<ItemPedido> itens) {
        this.cliente = cliente;
        this.data = LocalDate.now();
        this.addItensPedido(itens);
    }


    public List<ItemPedido> getItens() {
        return itens;
    }

    public void addItensPedido(List<ItemPedido> itemPedido) {
        this.itens.addAll(itemPedido);
        itemPedido.forEach(item -> item.setPedido(this));
        calculaTotalGasto();  //TODO - refactor
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void aplicarDesconto(Long quantidadeCompraCliente) {
        desconto.aplicar(quantidadeCompraCliente);
        calculaTotalGasto();
    }

    public Long getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public PedidoDesconto getDesconto() {
        return desconto;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private void calculaTotalGasto() {
        valorTotalCompra = this.getItens().stream().map(ItemPedido::getValorPago).reduce(BigDecimal.ZERO, BigDecimal::add);
        valorTotalPago = valorTotalCompra.multiply(new BigDecimal("1").subtract(desconto.getDesconto()));
    }

    public BigDecimal getValorTotalPago() {
        return valorTotalPago;
    }

    public BigDecimal getValorTotalCompra() {
        return valorTotalCompra;
    }

    public BigDecimal getValorDesconto() {
        calculaTotalGasto();
        return valorTotalCompra.multiply(desconto.getDesconto());
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", data=" + data +
                ", cliente=" + cliente +
                ", desconto=" + desconto.getDesconto() +
                ", tipoDesconto=" + desconto.getTipoDesconto() +
                ", itens=" + itens +
                ", total=" + valorTotalPago +
                '}';
    }
}
