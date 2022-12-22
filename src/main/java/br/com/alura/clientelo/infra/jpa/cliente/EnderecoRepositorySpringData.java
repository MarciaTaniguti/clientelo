package br.com.alura.clientelo.infra.jpa.cliente;

import br.com.alura.clientelo.core.entity.cliente.Endereco;
import br.com.alura.clientelo.core.usecase.cliente.RepositorioDeEndereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EnderecoRepositorySpringData implements RepositorioDeEndereco {
	@Autowired
	private EnderecoRepositoryJPA repository;

	@Override
	public Endereco cadastrar(Endereco endereco) {
		return repository.save(endereco);
	}
}
