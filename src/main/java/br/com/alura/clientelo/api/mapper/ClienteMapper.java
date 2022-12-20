package br.com.alura.clientelo.api.mapper;

import br.com.alura.clientelo.api.form.ClienteForm;
import br.com.alura.clientelo.dto.ClienteDto;
import br.com.alura.clientelo.core.entity.cliente.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface ClienteMapper {
	Cliente toModel(ClienteForm clienteDto);
	ClienteDto toDto(Cliente cliente);
}
