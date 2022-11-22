package br.com.alura.clientelo.orm;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "CLIENTE")
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
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Pedido> pedidos = new ArrayList<>();

	@Deprecated
	public Cliente() {
	}

	public Cliente(String nome, String cpf, String telefone, Endereco endereco) {
		this(nome,cpf,telefone,endereco,null);
	}

	public Cliente(String nome, String cpf, String telefone, Endereco endereco, List<Pedido> pedidos) {
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.endereco = endereco;
		this.pedidos = pedidos;
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

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void addPedido(Pedido pedido) {
		this.pedidos.add(pedido);
		pedido.setCliente(this);
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
