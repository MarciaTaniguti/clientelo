package br.com.alura.clientelo.core.usecase.cliente;

import br.com.alura.clientelo.core.usecase.dto.EnderecoDto;
import br.com.alura.clientelo.core.entity.cliente.Endereco;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface EnderecoMapper {
	EnderecoDto toDto(Endereco endereco);
	Endereco toModel(EnderecoDto enderecoDto);
}
