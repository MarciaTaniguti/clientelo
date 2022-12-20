package br.com.alura.clientelo.dto;

import br.com.alura.clientelo.core.entity.categoria.StatusCategoria;

public record CategoriaDto(
		String nome,
		StatusCategoria status
) {
}
