package br.com.alura.clientelo.dto;

public record EnderecoDto(
		String rua,
		String numero,
		String complemento,
		String bairro,
		String cidade,
		String estado) {
}
