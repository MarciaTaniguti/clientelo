package br.com.alura.clientelo.service;

import br.com.alura.clientelo.api.exception.ClienteNotFoundException;
import br.com.alura.clientelo.api.form.ItemPedidoForm;
import br.com.alura.clientelo.api.form.PedidoForm;
import br.com.alura.clientelo.api.mapper.PedidoMapper;
import br.com.alura.clientelo.orm.Cliente;
import br.com.alura.clientelo.orm.ItemPedido;
import br.com.alura.clientelo.orm.Pedido;
import br.com.alura.clientelo.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class CrudPedidoService {
	@Autowired
	private PedidoRepository repository;
	@Autowired
	private CrudItemPedidoService itemPedidoService;
	@Autowired
	private CrudClienteService clienteService;

	@Transactional
	public PedidoForm cadastrar(PedidoForm pedidoForm) {

		List<ItemPedido> itemPedido = itemPedidoService.buscarItensPedidoPorId(
				pedidoForm.itens()
						.stream().map(ItemPedidoForm::id).toList());

		Optional<Cliente> cliente = clienteService.buscaPorId(pedidoForm.idCliente());
		if (cliente.isEmpty()) {
			throw new ClienteNotFoundException();
		}

		Pedido pedido = new Pedido(cliente.get(), itemPedido);
		Long qtdComprasCliente = repository.quantidadePedidoPorCliente(pedidoForm.idCliente());
		pedido.aplicarDesconto(qtdComprasCliente);
		repository.save(pedido);
		return pedidoForm;
	}

	public Long quantidadePedidosPorCliente(Long clienteId) {
		return repository.quantidadePedidoPorCliente(clienteId);
	}
}
