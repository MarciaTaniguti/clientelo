package br.com.alura.clientelo.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Column(nullable = false)
	private String rua;
	@NotNull
	@Column(nullable = false)
	private String numero;
	private String complemento;
	private String bairro;
	@NotNull
	@Column(nullable = false)
	private String cidade;
	@NotNull
	@Column(nullable = false)
	private String estado;

	public Endereco(String rua, String numero, String complemento, String bairro, String cidade, String estado) {
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}

	public String getRua() {
		return rua;
	}

	public String getNumero() {
		return numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public String getEstado() {
		return estado;
	}
}
