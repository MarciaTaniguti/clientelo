package br.com.alura.clientelo.core.usecase.exception;

import java.io.Serial;

public class ProdutoSemEstoqueException extends RuntimeException {
	@Serial
	private static final long serialVersionUID = 1L;

	public ProdutoSemEstoqueException(String message) {
		super(message);
	}
}
