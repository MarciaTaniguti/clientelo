package br.com.alura.clientelo.infra.api.rest.form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public record LoginForm (
		String email,
		String senha) {

	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(email, senha);
	}
}
