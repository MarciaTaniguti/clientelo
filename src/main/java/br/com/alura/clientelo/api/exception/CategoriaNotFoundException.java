package br.com.alura.clientelo.api.exception;

import java.io.Serial;

public class CategoriaNotFoundException extends RuntimeException{
	@Serial
	private static final long serialVersionUID = 1L;

	public CategoriaNotFoundException(String id) {
		super("Categoria %s não encontrado!".formatted(id));
	}


}
