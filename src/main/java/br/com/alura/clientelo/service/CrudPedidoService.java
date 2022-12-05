package br.com.alura.clientelo.service;

import br.com.alura.clientelo.api.exception.ClienteNotFoundException;
import br.com.alura.clientelo.api.exception.ProdutoNotFoundException;
import br.com.alura.clientelo.api.exception.ProdutoSemEstoqueException;
import br.com.alura.clientelo.api.form.ItemPedidoForm;
import br.com.alura.clientelo.api.form.PedidoForm;
import br.com.alura.clientelo.orm.Cliente;
import br.com.alura.clientelo.orm.ItemPedido;
import br.com.alura.clientelo.orm.Pedido;
import br.com.alura.clientelo.orm.Produto;
import br.com.alura.clientelo.repository.PedidoRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class CrudPedidoService {
	@Autowired
	private PedidoRepository repository;
	@Autowired
	private CrudItemPedidoService itemPedidoService;
	@Autowired
	private CrudClienteService clienteService;
	@Autowired
	private CrudProdutoService produtoService;

	@Transactional
	public PedidoForm cadastrar(PedidoForm pedidoForm) {
		Optional<Cliente> cliente = clienteService.buscaPorId(pedidoForm.idCliente());

		if (cliente.isEmpty()) {
			throw new ClienteNotFoundException();
		}
		List<ItemPedidoForm> listaOrdenadaItemPedidos = pedidoForm.itens().stream().sorted(Comparator.comparingLong(ItemPedidoForm::id)).toList();
		pedidoForm.itens().clear();
		pedidoForm.itens().addAll(listaOrdenadaItemPedidos);


		List<Long> idProdutosCompra = pedidoForm.itens().stream().map(ItemPedidoForm::id).toList();
		List<Produto> listaProdutosEncontrados = produtoService.listaTodos(idProdutosCompra);
		List<Long> idsEncontrados = listaProdutosEncontrados.stream().map(Produto::getId).toList();
		List<ItemPedido> itemPedidos = new ArrayList<>();

		Collection<Long> subtract = CollectionUtils.subtract(idProdutosCompra, idsEncontrados);

		if (subtract.size() != 0) {
			throw new ProdutoNotFoundException("Produto não encontrado: IDs " + subtract);
		}

		for(int i = 0; i<idProdutosCompra.size(); i++) {
			if (listaProdutosEncontrados.get(i).getQuantidadeEstoque() < pedidoForm.itens().get(i).quantidade()) {
				throw new ProdutoSemEstoqueException("Produto sem estoque: ID " + listaProdutosEncontrados.get(i).getId());
			}

			itemPedidos.add(new ItemPedido(pedidoForm.itens().get(i).quantidade(), listaProdutosEncontrados.get(i)));
		}

		Pedido pedido = new Pedido(cliente.get(), itemPedidos);
		Long qtdComprasCliente = repository.quantidadePedidoPorCliente(pedidoForm.idCliente());
		pedido.aplicarDesconto(qtdComprasCliente);
		repository.save(pedido);
		return pedidoForm;
	}

	public Long quantidadePedidosPorCliente(Long clienteId) {
		return repository.quantidadePedidoPorCliente(clienteId);
	}
}
