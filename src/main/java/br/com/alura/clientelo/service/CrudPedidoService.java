package br.com.alura.clientelo.service;

import br.com.alura.clientelo.orm.Pedido;
import br.com.alura.clientelo.repository.PedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class CrudPedidoService {
	private final PedidoRepository repository;

	public CrudPedidoService(PedidoRepository repository) {
		this.repository = repository;
	}

	public void cadastrar(Pedido pedido) {
		repository.save(pedido);
	}
}
