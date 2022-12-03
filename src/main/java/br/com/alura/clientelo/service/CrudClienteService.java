package br.com.alura.clientelo.service;

import br.com.alura.clientelo.orm.Cliente;
import br.com.alura.clientelo.repository.ClienteRepository;
import org.apache.commons.collections4.IterableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CrudClienteService {
	@Autowired
	private ClienteRepository repository;
	private final Logger LOG = LoggerFactory.getLogger(CrudClienteService.class);

	public Optional<Cliente> buscaPorId(Long id) {
		return repository.findById(id);
	}

	@Transactional
	public void cadastrar(Cliente cliente) {
		repository.save(cliente);
	}

	@Transactional
	public void remove(Cliente cliente) {
		repository.delete(cliente);
		LOG.info("DELETADO!");
	}

	@Transactional
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
