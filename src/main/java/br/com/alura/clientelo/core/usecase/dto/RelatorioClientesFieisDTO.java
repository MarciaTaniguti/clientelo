package br.com.alura.clientelo.core.usecase.dto;

import java.math.BigDecimal;

public record RelatorioClientesFieisDTO(
		String nome,
		Long quantidadePedidos,
		BigDecimal montanteGasto) {
}
