package br.com.alura.clientelo.api.mapper;

import br.com.alura.clientelo.api.form.ProdutoForm;
import br.com.alura.clientelo.orm.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface ProdutoMapper {
	@Mapping(source = "idCategoria", target = "categoria.id")
	Produto toModel(ProdutoForm produto);
	@Mapping(source = "categoria.id", target = "idCategoria")
	ProdutoForm toDto(Produto produto);

}
