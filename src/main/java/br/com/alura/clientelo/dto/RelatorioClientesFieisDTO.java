package br.com.alura.clientelo.dto;

import java.math.BigDecimal;

public record RelatorioClientesFieisDTO(
		String nome,
		Long quantidadePedidos,
		BigDecimal montanteGasto) {
}
