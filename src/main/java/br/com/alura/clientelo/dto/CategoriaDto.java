package br.com.alura.clientelo.dto;

import br.com.alura.clientelo.orm.StatusCategoria;

public record CategoriaDto(
		String nome,
		StatusCategoria status
) {
}
