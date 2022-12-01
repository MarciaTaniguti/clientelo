package br.com.alura.clientelo.api.exception;

public record ArgumentNotValid(
	String paramName,
	String message) {
}
