package br.com.alura.clientelo.api.form;

import br.com.alura.clientelo.orm.StatusCategoria;
import org.hibernate.validator.constraints.Length;

public record CategoriaForm(
	@Length(min = 2, message = "O nome deve possuir ao menos 2 caracteres")
	String nome,
	StatusCategoria status
) {

	public CategoriaForm(String nome) {
		this(nome,StatusCategoria.ATIVA);
	}
}
