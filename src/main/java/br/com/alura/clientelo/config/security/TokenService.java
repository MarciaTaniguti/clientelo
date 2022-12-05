package br.com.alura.clientelo.config.security;

import br.com.alura.clientelo.orm.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

	@Value("${spring.security.expiration-date}")
	private String tempoExpiracao;

	@Value("${spring.security.secret}")
	private String secret;

	public String gerarToken(Authentication authentication) {
		Usuario usuario = (Usuario) authentication.getPrincipal();
		Date agora = new Date();
		return Jwts.builder()
				.setIssuer("Alura Elo7")
				.setSubject(usuario.getId().toString())
				.setIssuedAt(new Date())
				.setExpiration(new Date(agora.getTime() + Long.parseLong(tempoExpiracao)))
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public boolean isTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getIdUsuario(String token) {
		return Long.parseLong(Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject());
	}
}
