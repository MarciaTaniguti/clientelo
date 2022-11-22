package br.com.alura.clientelo.orm;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "PRODUTO")
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Column(nullable = false)
	private final String nome;
	private final String descricao;
	@NotNull
	@Column(name = "quantidade_estoque", nullable = false)
	private final Integer quantidadeEstoque;
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Categoria categoria;
	@NotNull
	@Column(nullable = false)
	private final BigDecimal preco;

	public Produto(String nome, String descricao, Integer quantidadeEstoque, Categoria categoria, BigDecimal preco) {
		this.nome = nome;
		this.descricao = descricao;
		this.quantidadeEstoque = quantidadeEstoque;  //TODO - Controlar estoque
		this.categoria = categoria;
		this.preco = preco;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Produto{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", descricao='" + descricao + '\'' +
				", quantidadeEstoque=" + quantidadeEstoque +
				", categoria=" + categoria +
				", preco=" + preco +
				'}';
	}
}
