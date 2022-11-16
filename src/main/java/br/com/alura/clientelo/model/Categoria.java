package br.com.alura.clientelo.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "CATEGORIA")
public class Categoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Column(nullable = false)
	private String nome;
	@NotNull
	@Column(nullable = false)
	private StatusCategoria status;

	public Categoria(String nome) {
		this.nome = nome;
		this.status = StatusCategoria.ATIVA;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public StatusCategoria getStatus() {
		return status;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setStatus(StatusCategoria status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Categoria{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", status=" + status +
				'}';
	}
}
