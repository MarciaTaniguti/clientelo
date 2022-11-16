package br.com.alura.clientelo.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "PEDIDO")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private final LocalDate data;
    @ManyToOne
    private final Cliente cliente;
    private BigDecimal desconto;
    @Column(name = "tipo_desconto")
    @Enumerated(EnumType.STRING)
    private TipoDescontoPedido tipoDesconto;
    @Column(name = "item_pedido")
    @OneToMany(mappedBy = "id")
    private List<ItemPedido> itemPedido;
    //private final BigDecimal total;


    public Pedido(Cliente cliente, BigDecimal desconto, TipoDescontoPedido tipoDesconto, List<ItemPedido> itemPedido) {
        this.cliente = cliente;
        this.desconto = desconto;
        this.tipoDesconto = tipoDesconto;
        this.itemPedido = itemPedido;
        this.data = LocalDate.now();
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

    public List<ItemPedido> getItemPedido() {
        return itemPedido;
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
