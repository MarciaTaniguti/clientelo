package br.com.alura.clientelo.core.usecase.dto;

import br.com.alura.clientelo.core.entity.categoria.StatusCategoria;

public record CategoriaDto(
		String nome,
		StatusCategoria status
) {
}
