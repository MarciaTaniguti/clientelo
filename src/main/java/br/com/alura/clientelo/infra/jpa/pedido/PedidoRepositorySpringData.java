package br.com.alura.clientelo.infra.jpa.pedido;

import br.com.alura.clientelo.core.entity.pedido.Pedido;
import br.com.alura.clientelo.core.entity.pedido.RepositorioDePedido;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class PedidoRepositorySpringData implements RepositorioDePedido {

	@Autowired
	private PedidoRepositoryJPA repository;

	@Override
	@Transactional
	public Pedido cadastrar(Pedido pedido) {
		return repository.save(pedido);
	}

	@Override
	public Optional<Pedido> buscarDetalhePedido(Long id) {
		return repository.findById(id);
	}

	@Override
	public List<Pedido> buscarTodos(List<Long> ids) {
		return IterableUtils.toList(repository.findAllById(ids));
	}

	@Override
	public Long qtdPedidoPorCliente(Long idCliente) {
		return repository.quantidadePedidoPorCliente(idCliente);
	}

	@Override
	public Optional<Pedido> buscarPorId(Long id) {
		return repository.findById(id);
	}

	@Override
	public List<Pedido> listaTodos() {
		return IterableUtils.toList(repository.findAll());
	}
}
