package br.com.alura.clientelo.api.mapper;

import br.com.alura.clientelo.api.dto.CategoriaDto;
import br.com.alura.clientelo.orm.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")
public interface CategoriaMapper {
	Categoria toModel(CategoriaDto categoria);

	CategoriaDto toDto(Categoria categoria);
}
