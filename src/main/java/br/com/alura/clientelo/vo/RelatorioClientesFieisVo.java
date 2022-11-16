package br.com.alura.clientelo.vo;

import java.math.BigDecimal;

public record RelatorioClientesFieisVo(
		String nome,
		Long quantidadePedidos,
		BigDecimal montanteGasto) {
}
