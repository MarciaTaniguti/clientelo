package br.com.alura.clientelo.service;

import br.com.alura.clientelo.orm.ItemPedido;
import br.com.alura.clientelo.repository.ItemPedidoRepository;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrudItemPedidoService {
	private final ItemPedidoRepository repository;

	@Autowired
	public CrudItemPedidoService(ItemPedidoRepository repository) {
		this.repository = repository;
	}

	public List<ItemPedido> buscarItensPedidoPorId(List<Long> ids) {
		return IterableUtils.toList(repository.findAllById(ids));
	}
}
