package br.com.alura.clientelo.core.usecase.dto;

import br.com.alura.clientelo.core.entity.cliente.Cliente;
import br.com.alura.clientelo.core.usecase.cliente.ClienteMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Transient;

public class ClienteDto {
	@Autowired
	private ClienteMapper mapper;
	private String nome;
	private String cpf;
	private String telefone;
	private EnderecoDto endereco;

	public ClienteDto(String nome, String cpf, String telefone, EnderecoDto endereco) {
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.endereco = endereco;
	}

	public Cliente toModel() {
		return mapper.toModel(this);
	}

	public String getNome() {
		return nome;
	}

	public EnderecoDto getEndereco() {
		return endereco;
	}

	public String getCpf() {
		return cpf;
	}

	public String getTelefone() {
		return telefone;
	}
}
