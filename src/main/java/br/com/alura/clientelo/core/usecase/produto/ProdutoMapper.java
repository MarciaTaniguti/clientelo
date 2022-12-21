package br.com.alura.clientelo.core.usecase.produto;

import br.com.alura.clientelo.api.form.CadastroProdutoForm;
import br.com.alura.clientelo.core.usecase.dto.ProdutoDto;
import br.com.alura.clientelo.core.entity.produto.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface ProdutoMapper {
	@Mapping(source = "idCategoria", target = "categoria.id")
	Produto toModel(CadastroProdutoForm produto);
	ProdutoDto toDto(Produto produto);
}
