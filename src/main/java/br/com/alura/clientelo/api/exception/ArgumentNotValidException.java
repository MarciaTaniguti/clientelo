package br.com.alura.clientelo.api.exception;

public record ArgumentNotValidException(
	String paramName,
	String message) {
}
