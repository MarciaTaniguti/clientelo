package br.com.alura.clientelo.infra.api.rest.produto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record DetalheProdutoForm(
	Long id,
	String nome,
	String categoria,
	Long quantidade,
	BigDecimal precoUnitario,
	BigDecimal valor,
	BigDecimal desconto
) {
	@Override
	public BigDecimal desconto() {
		return desconto.setScale(2, RoundingMode.HALF_EVEN);
	}
}
