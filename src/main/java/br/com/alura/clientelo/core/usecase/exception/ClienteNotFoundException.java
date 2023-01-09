package br.com.alura.clientelo.core.usecase.exception;

import java.io.Serial;
import java.util.HashMap;

public class ClienteNotFoundException extends RuntimeException{
	@Serial
	private static final long serialVersionUID = 1L;

	public ClienteNotFoundException() {
		super("Cliente não encontrado!");
	}
}
