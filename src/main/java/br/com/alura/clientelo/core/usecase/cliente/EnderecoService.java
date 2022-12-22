package br.com.alura.clientelo.core.usecase.cliente;

import br.com.alura.clientelo.core.entity.cliente.Endereco;
import br.com.alura.clientelo.core.usecase.dto.EnderecoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnderecoService {
	@Autowired
	private RepositorioDeEndereco repository;
	@Autowired
	private EnderecoMapper mapper;

	@Transactional
	public EnderecoDto cadastrar(Endereco endereco) {
		repository.cadastrar(endereco);
		return mapper.toDto(endereco);
	}
}
