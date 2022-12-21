package br.com.alura.clientelo.core.usecase.dto;

import java.math.BigDecimal;

public record RelatorioVendasPorCategoriaDTO(
	String nomeCategoria,
	Long quantidadeVendida,
	BigDecimal montanteVendido) {

	@Override
	public String toString() {
		return "CATEGORIA = " + nomeCategoria +
				", quantidade = " + quantidadeVendida +
				", montante = " + montanteVendido +"\n";
	}
}
