package br.com.alura.clientelo.api.exception;

import java.io.Serial;

public class PedidoNotFoundException extends RuntimeException{
	@Serial
	private static final long serialVersionUID = 1L;

	public PedidoNotFoundException(String message) {
		super(message);
	}
}
