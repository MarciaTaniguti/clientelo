package br.com.alura.clientelo.api.mapper;

import br.com.alura.clientelo.api.form.CadastroProdutoForm;
import br.com.alura.clientelo.dto.ProdutoDto;
import br.com.alura.clientelo.orm.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface ProdutoMapper {
	@Mapping(source = "idCategoria", target = "categoria.id")
	Produto toModel(CadastroProdutoForm produto);
	ProdutoDto toDto(Produto produto);
}
