package br.com.alura.clientelo.core.usecase.exception;

import java.io.Serial;

public class ProdutoNotFoundException extends RuntimeException {
	@Serial
	private static final long serialVersionUID = 1L;

	public ProdutoNotFoundException(String message) {
		super(message);
	}
}
