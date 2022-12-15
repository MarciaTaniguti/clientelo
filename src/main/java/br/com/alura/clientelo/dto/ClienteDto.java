package br.com.alura.clientelo.dto;

public record ClienteDto(
		String nome,
		String cpf,
		String telefone,
		EnderecoDto endereco
) {
}
