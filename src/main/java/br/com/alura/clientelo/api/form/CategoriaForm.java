package br.com.alura.clientelo.api.form;

import br.com.alura.clientelo.core.entity.categoria.Categoria;
import br.com.alura.clientelo.core.entity.categoria.StatusCategoria;
import br.com.alura.clientelo.core.usecase.categoria.CategoriaMapper;
import br.com.alura.clientelo.core.usecase.pedido.Default;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Transient;

public class CategoriaForm {
	@Length(min = 2, message = "O nome deve possuir ao menos 2 caracteres")
	private final String nome;
	private StatusCategoria status;
	@Transient	@Autowired
	private CategoriaMapper mapper;

	@Default
	public CategoriaForm(String nome) {
		this(nome, StatusCategoria.ATIVA);
	}

	public CategoriaForm(String nome, StatusCategoria status) {
		this.nome = nome;
		this.status = status;
	}

	public Categoria toModel() {
		return mapper.toModel(this);
	}

	public String getNome() {
		return nome;
	}

	public StatusCategoria getStatus() {
		return status;
	}

	public CategoriaMapper getMapper() {
		return mapper;
	}
}
