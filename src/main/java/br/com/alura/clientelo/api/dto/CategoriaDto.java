package br.com.alura.clientelo.api.dto;

import br.com.alura.clientelo.orm.StatusCategoria;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public record CategoriaDto (
	@Length(min = 2, message = "O nome deve possuir ao menos 2 caracteres")
	String nome,
	StatusCategoria status
) {

	public CategoriaDto(String nome) {
		this(nome,StatusCategoria.ATIVA);
	}
}
