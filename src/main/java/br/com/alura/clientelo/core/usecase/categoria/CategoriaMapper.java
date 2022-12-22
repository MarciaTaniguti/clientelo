package br.com.alura.clientelo.core.usecase.categoria;

import br.com.alura.clientelo.api.form.CategoriaForm;
import br.com.alura.clientelo.core.entity.categoria.Categoria;
import br.com.alura.clientelo.core.usecase.dto.CategoriaDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface CategoriaMapper {
	Categoria toModel(CategoriaForm categoria);

	CategoriaDto toDto(Categoria categoria);
	List<CategoriaDto> toDto(List<Categoria> categorias);

	CategoriaForm toForm(Categoria categoria);
}
