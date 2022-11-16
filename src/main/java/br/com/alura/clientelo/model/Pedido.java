package br.com.alura.clientelo.model;

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
    private final LocalDate data;
    @ManyToOne
    private Cliente cliente;
    private BigDecimal desconto;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_desconto")
    private final TipoDescontoPedido tipoDesconto;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens = new ArrayList<>();
    //private final BigDecimal total;


    public Pedido(Cliente cliente, BigDecimal desconto, TipoDescontoPedido tipoDesconto, List<ItemPedido> itens) {
        this.cliente = cliente;
        this.tipoDesconto = tipoDesconto;
        this.itens = itens;
        this.data = LocalDate.now();
        this.setDesconto(desconto);
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

    public BigDecimal getDesconto() {
        return desconto;
    }

    public TipoDescontoPedido getTipoDesconto() {
        return tipoDesconto;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void addItemPedido(ItemPedido itemPedido) {
        this.itens.add(itemPedido);
        itemPedido.setPedido(this);
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setDesconto(BigDecimal desconto) {
        if (desconto.compareTo(new BigDecimal("0.81")) > 0) {
            throw new RuntimeException("Não é possível ter um desconto acima de 80%");
        }
        this.desconto = desconto;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", data=" + data +
                ", cliente=" + cliente +
                ", desconto=" + desconto +
                ", tipoDesconto=" + tipoDesconto +
                '}';
    }
}
