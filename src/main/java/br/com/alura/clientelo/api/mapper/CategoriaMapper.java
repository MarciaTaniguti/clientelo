package br.com.alura.clientelo.api.mapper;

import br.com.alura.clientelo.api.form.CategoriaForm;
import br.com.alura.clientelo.core.entity.categoria.Categoria;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface CategoriaMapper {
	Categoria toModel(CategoriaForm categoria);

	CategoriaForm toDto(Categoria categoria);
}
