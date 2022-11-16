package br.com.alura.clientelo.model;

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
	@OneToOne
	@JoinColumn(name = "categoria", referencedColumnName = "id")
	private Categoria categoria;
	@NotNull
	@Column(nullable = false)
	private BigDecimal preco;

	public Produto(String nome, String descricao, Integer quantidadeEstoque, Categoria categoria, BigDecimal preco) {
		this.nome = nome;
		this.descricao = descricao;
		this.quantidadeEstoque = quantidadeEstoque;
		this.categoria = categoria;
		this.preco = preco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
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
