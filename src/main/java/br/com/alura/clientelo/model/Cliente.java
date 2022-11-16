package br.com.alura.clientelo.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Column(nullable = false)
	private String nome;
	@NotNull
	@Column(nullable = false)
	private String cpf;
	@NotNull
	@Column(nullable = false)
	private String telefone;
	@NotNull
	@OneToOne
	private Endereco endereco;

	public Cliente(String nome, String cpf, String telefone, Endereco endereco) {
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.endereco = endereco;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Cliente cliente = (Cliente) o;
		return Objects.equals(id, cliente.id) && Objects.equals(nome, cliente.nome) && Objects.equals(cpf, cliente.cpf) && Objects.equals(telefone, cliente.telefone) && Objects.equals(endereco, cliente.endereco);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nome, cpf, telefone, endereco);
	}
}
