package br.com.alura.clientelo.core.usecase.dto;

public record ClienteDto(
		String nome,
		String cpf,
		String telefone,
		EnderecoDto endereco
) {
}
