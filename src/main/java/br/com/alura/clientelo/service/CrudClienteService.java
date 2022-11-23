package br.com.alura.clientelo.service;

import br.com.alura.clientelo.orm.Cliente;
import br.com.alura.clientelo.repository.ClienteRepository;
import org.apache.commons.collections4.IterableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CrudClienteService {
	private final ClienteRepository repository;
	private final Logger LOG = LoggerFactory.getLogger(CrudClienteService.class);

	public CrudClienteService(ClienteRepository repository) {
		this.repository = repository;
	}

	public Optional<Cliente> buscaPorId(Long id) {
		return repository.findById(id);
	}

	public void cadastrar(Cliente cliente) {
		repository.save(cliente);
	}

	public void remove(Cliente cliente) {
		repository.delete(cliente);
		LOG.info("DELETADO!");
	}

	public void atualizar(Cliente cliente) {
		Optional<Cliente> clienteEncontrado = repository.findById(cliente.getId());

		if (clienteEncontrado.isEmpty()) {
			LOG.info("Cliente não encontrado!");
			return;
		}

		repository.save(cliente);
		LOG.info("ATUALIZADO!");
	}

	public List<Cliente> listaTodos() {
		return IterableUtils.toList(repository.findAll());
	}

	public List<Cliente> buscaPorNome(String nome) {
		return repository.findByNome(nome);
	}
}
