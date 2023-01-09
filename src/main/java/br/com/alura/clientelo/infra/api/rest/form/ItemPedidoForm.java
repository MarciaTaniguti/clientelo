package br.com.alura.clientelo.infra.api.rest.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public record ItemPedidoForm(
		@NotNull
		Long id,
		@Positive
		Long quantidade) {
}
