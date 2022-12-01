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
	private String nome;
	private String descricao;
	@NotNull
	@Column(name = "quantidade_estoque", nullable = false)
	private Integer quantidadeEstoque;
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Categoria categoria;
	@NotNull
	@Column(nullable = false)
	private BigDecimal preco;

	@Deprecated
	public Produto() {
	}

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

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
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
