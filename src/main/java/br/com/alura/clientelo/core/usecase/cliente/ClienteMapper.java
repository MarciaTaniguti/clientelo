package br.com.alura.clientelo.core.usecase.cliente;

import br.com.alura.clientelo.infra.api.rest.cliente.ClienteForm;
import br.com.alura.clientelo.core.entity.cliente.Endereco;
import br.com.alura.clientelo.core.usecase.dto.ClienteDto;
import br.com.alura.clientelo.core.entity.cliente.Cliente;
import br.com.alura.clientelo.core.usecase.dto.EnderecoDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface ClienteMapper {
	Cliente toModel(ClienteForm clienteForm);
	Cliente toModel(ClienteDto clienteDto);
	ClienteDto toDto(Cliente cliente);
	List<ClienteDto> toDto(List<Cliente> clientes);

	default Endereco map(EnderecoDto enderecoDto) {
		if (enderecoDto == null) {
			return null;
		}
		return new Endereco(enderecoDto.rua(),enderecoDto.numero(), enderecoDto.complemento(), enderecoDto.bairro(), enderecoDto.cidade(), enderecoDto.estado());
	}

}
