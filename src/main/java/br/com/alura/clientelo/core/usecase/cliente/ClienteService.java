package br.com.alura.clientelo.core.usecase.cliente;

import br.com.alura.clientelo.core.usecase.exception.ClienteNotFoundException;
import br.com.alura.clientelo.infra.api.rest.cliente.ClienteForm;
import br.com.alura.clientelo.core.entity.cliente.RepositorioDeCliente;
import br.com.alura.clientelo.core.usecase.dto.ClienteDto;
import br.com.alura.clientelo.core.entity.cliente.Cliente;
import br.com.alura.clientelo.infra.jpa.pedido.PedidoRepositoryJPA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
	@Autowired
	private RepositorioDeCliente repository;
	@Autowired
	private PedidoRepositoryJPA pedidoRepository;

	private final Logger LOG = LoggerFactory.getLogger(ClienteService.class);

	public Cliente buscaPorId(Long id) {
		Optional<Cliente> cliente = repository.buscarPorId(id);
		if (cliente.isEmpty()) {
			throw new ClienteNotFoundException();
		}
		Long quantidadeCompras = pedidoRepository.quantidadePedidoPorCliente(id);
		cliente.ifPresent(c -> c.setQuantidadeCompras(quantidadeCompras));
		return cliente.get();
	}

	@Transactional
	public Cliente cadastrar(Cliente cliente) {
		repository.cadastrar(cliente);
		return cliente;
	}

	@Transactional
	public void remove(Cliente cliente) {
		repository.remover(cliente);
	}

	@Transactional
	public Cliente atualizar(Cliente cliente) {
		Optional<Cliente> clienteAtualizado = repository.atualizar(cliente);

		if (clienteAtualizado.isEmpty()) {
			throw new ClienteNotFoundException();
		}
		return clienteAtualizado.get();
	}

	public List<Cliente> listaTodos() {
		return repository.listaTodos();
	}

	public Cliente buscaPorNome(String nome) {
		Optional<Cliente> cliente = repository.buscarPorNome(nome);
		if (cliente.isEmpty()) {
			throw new ClienteNotFoundException();
		}
		return cliente.get();
	}
}
