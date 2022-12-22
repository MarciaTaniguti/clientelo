package br.com.alura.clientelo.core.usecase.cliente;

import br.com.alura.clientelo.api.exception.ClienteNotFoundException;
import br.com.alura.clientelo.api.form.ClienteForm;
import br.com.alura.clientelo.core.usecase.dto.ClienteDto;
import br.com.alura.clientelo.core.entity.cliente.Cliente;
import br.com.alura.clientelo.infra.jpa.cliente.ClienteRepositoryJPA;
import br.com.alura.clientelo.repository.PedidoRepository;
import org.apache.commons.collections4.IterableUtils;
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
	private PedidoRepository pedidoRepository;
	@Autowired
	private ClienteMapper mapper;

	private final Logger LOG = LoggerFactory.getLogger(ClienteService.class);

	public ClienteDto buscaPorId(Long id) {
		Optional<Cliente> cliente = repository.buscarPorId(id);
		if (cliente.isEmpty()) {
			throw new ClienteNotFoundException();
		}
		Long quantidadeCompras = pedidoRepository.quantidadePedidoPorCliente(id);
		cliente.ifPresent(c -> c.setQuantidadeCompras(quantidadeCompras));
		return mapper.toDto(cliente.get());
	}

	public ClienteDto cadastrar(ClienteForm clienteForm) {
		Cliente cliente = mapper.toModel(clienteForm);
		return cadastrar(cliente);
	}

	@Transactional
	public ClienteDto cadastrar(Cliente cliente) {
		repository.cadastrar(cliente);
		return mapper.toDto(cliente);
	}

	@Transactional
	public void remove(Cliente cliente) {
		repository.remover(cliente);
	}

	@Transactional
	public ClienteDto atualizar(Cliente cliente) {
		Optional<Cliente> clienteAtualizado = repository.atualizar(cliente);

		if (clienteAtualizado.isEmpty()) {
			throw new ClienteNotFoundException();
		}
		return mapper.toDto(clienteAtualizado.get());
	}

	public List<ClienteDto> listaTodos() {
		List<Cliente> clientes = repository.listaTodos();
		return mapper.toDto(clientes);
	}

	public ClienteDto buscaPorNome(String nome) {
		Optional<Cliente> cliente = repository.buscarPorNome(nome);
		if (cliente.isEmpty()) {
			throw new ClienteNotFoundException();
		}
		return mapper.toDto(cliente.get());
	}
}
