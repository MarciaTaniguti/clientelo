package br.com.alura.clientelo.api.mapper;

import br.com.alura.clientelo.dto.EnderecoDto;
import br.com.alura.clientelo.core.entity.cliente.Endereco;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface EnderecoMapper {
	EnderecoDto toDto(Endereco endereco);
	Endereco toModel(EnderecoDto enderecoDto);
}
