package br.com.alura.clientelo.infra.config.security;

import br.com.alura.clientelo.core.entity.usuario.Usuario;
import br.com.alura.clientelo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

	@Autowired
	UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = repository.findByEmail(username);

		if (usuario.isEmpty()) {
			throw new UsernameNotFoundException("Dados inválidos");
		}

		return usuario.get();
	}
}
