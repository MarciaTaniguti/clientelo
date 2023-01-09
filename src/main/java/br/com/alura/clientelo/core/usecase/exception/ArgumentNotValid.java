package br.com.alura.clientelo.core.usecase.exception;

public record ArgumentNotValid(
	String paramName,
	String message) {
}
