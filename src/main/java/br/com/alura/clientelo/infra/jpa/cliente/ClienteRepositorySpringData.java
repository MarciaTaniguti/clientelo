package br.com.alura.clientelo.infra.jpa.cliente;

import br.com.alura.clientelo.api.exception.ClienteNotFoundException;
import br.com.alura.clientelo.core.entity.cliente.Cliente;
import br.com.alura.clientelo.core.usecase.cliente.RepositorioDeCliente;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public class ClienteRepositorySpringData implements RepositorioDeCliente {

	@Autowired
	private ClienteRepositoryJPA repository;

	@Override
	public Optional<Cliente> buscarPorId(Long id) {
		return repository.findById(id);
	}

	@Override
	public Cliente cadastrar(Cliente cliente) {
		return repository.save(cliente);
	}

	@Override
	public Optional<Cliente> atualizar(Cliente cliente) {
		if (buscarPorCpf(cliente.getCpf()).isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(repository.save(cliente));
	}

	@Override
	public Optional<Cliente> buscarPorNome(String nome) {
		return repository.findByNome(nome);
	}

	@Override
	public Optional<Cliente> buscarPorCpf(String cpf) {
		return repository.findByCpf(cpf);
	}

	@Override
	public void remover(Cliente cliente) {
		repository.delete(cliente);
	}

	@Override
	public List<Cliente> listaTodos() {
		return IterableUtils.toList(repository.findAll());
	}
}
