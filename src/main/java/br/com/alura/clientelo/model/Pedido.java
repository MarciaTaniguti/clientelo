package br.com.alura.clientelo.model;

import jdk.javadoc.doclet.Reporter;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Pedido {
    private final String categoria;
    private final String produto;
    private final String cliente;
    private final BigDecimal preco;
    private final int quantidade;
    private final LocalDate data;
    private final BigDecimal total;

    public Pedido(String categoria, String produto, String cliente, BigDecimal preco, int quantidade, LocalDate data) {
        this.categoria = categoria;
        this.produto = produto;
        this.cliente = cliente;
        this.preco = preco;
        this.quantidade = quantidade;
        this.data = data;
        this.total = preco.multiply(new BigDecimal(quantidade));
    }

    public String getCategoria() {
        return categoria;
    }

    public String getProduto() {
        return produto;
    }

    public String getCliente() {
        return cliente;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public LocalDate getData() {
        return data;
    }

    public BigDecimal getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "categoria='" + categoria + '\'' +
                ", produto='" + produto + '\'' +
                ", cliente='" + cliente + '\'' +
                ", preco=" + preco +
                ", quantidade=" + quantidade +
                ", data=" + data +
                ", total=" + total +
                '}';
    }
}
