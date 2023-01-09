package br.com.alura.clientelo.core.entity.categoria;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "CATEGORIA")
public class Categoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Column(nullable = false, unique = true)
	private String nome;
	@NotNull
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusCategoria status;

	@Deprecated
	public Categoria() {
	}

	public Categoria(String nome) {
		this.nome = nome;
		this.status = StatusCategoria.ATIVA;
	}

	public Categoria(Long id, String nome) {
		this(nome);
		this.id = id;
	}

	public Categoria(Long id) {
		this.id = id;
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

	public void setId(Long id) {
		this.id = id;
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
