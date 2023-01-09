package br.com.alura.clientelo.core.usecase.pedido;

import br.com.alura.clientelo.core.entity.cliente.Cliente;
import br.com.alura.clientelo.core.entity.pedido.Pedido;
import br.com.alura.clientelo.core.entity.pedido.RepositorioDePedido;
import br.com.alura.clientelo.core.entity.pedido.item.ItemPedido;
import br.com.alura.clientelo.core.entity.produto.Produto;
import br.com.alura.clientelo.core.usecase.cliente.ClienteService;
import br.com.alura.clientelo.core.usecase.dto.ClienteDto;
import br.com.alura.clientelo.core.usecase.exception.PedidoNotFoundException;
import br.com.alura.clientelo.core.usecase.exception.ProdutoNotFoundException;
import br.com.alura.clientelo.core.usecase.exception.ProdutoSemEstoqueException;
import br.com.alura.clientelo.core.usecase.produto.ProdutoService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class PedidoService {
	@Autowired
	private RepositorioDePedido repository;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private ProdutoService produtoService;


	public Pedido cadastrar(Pedido pedido) {
		List<ItemPedido> itemPedidos = new ArrayList<>();

		Cliente cliente = validaClienteNaBase(pedido.getIdCliente());
		ordenarListaPedidoById(pedido);
		List<Produto> listaProdutosEncontrados = recuperarPedidosDaBase(pedido);
		validaEstoqueDosProdutos(pedido.getItens(), listaProdutosEncontrados, itemPedidos);

		Pedido pedido1 = new Pedido(cliente, itemPedidos);

		repository.cadastrar(pedido1);
		produtoService.atualiza(listaProdutosEncontrados);



		return pedido;
	}

	public Pedido buscarDetalhePedido(Long id) {
		Optional<Pedido> pedido = repository.buscarPorId(id);

		if (pedido.isEmpty()) {
			throw new PedidoNotFoundException("Pedido não encontrado!");
		}

		return pedido.get();
	}

	private void validaEstoqueDosProdutos(List<ItemPedido> itens, List<Produto> listaProdutosEncontrados, List<ItemPedido> itemPedidos) {
		for(int i = 0; i<itens.size(); i++) {
			Long quantidadeEstoque = listaProdutosEncontrados.get(i).getQuantidadeEstoque();
			Long quantidadePedido = itens.get(i).getQuantidade();
			long quantidadeRestante = quantidadeEstoque - quantidadePedido;
			if (quantidadeRestante < 0) {
				throw new ProdutoSemEstoqueException("Produto ID " + listaProdutosEncontrados.get(i).getId() + " - em estoque: " + quantidadeEstoque);
			}
			itemPedidos.add(new ItemPedido(quantidadePedido, listaProdutosEncontrados.get(i)));
			listaProdutosEncontrados.get(i).setQuantidadeEstoque(quantidadeRestante);
		}
	}

	private void ordenarListaPedidoById(Pedido pedido) {
		List<ItemPedido> listaOrdenadaItemPedidos = pedido.getItens().stream().sorted(Comparator.comparingLong(ItemPedido::getId)).toList();
		pedido.replaceItens(listaOrdenadaItemPedidos);
	}

	@Transactional(readOnly = true)
	private Cliente validaClienteNaBase(Long idCliente) {
		return clienteService.buscaPorId(idCliente);
	}

	@Transactional(readOnly = true)
	private List<Produto> recuperarPedidosDaBase(Pedido pedido) {
		List<Long> idProdutosCompra = pedido.getItens().stream().map(ItemPedido::getId).toList();
		List<Produto> listaProdutosEncontrados = produtoService.listaTodos(idProdutosCompra);
		List<Long> idsEncontrados = listaProdutosEncontrados.stream().map(Produto::getId).toList();

		Collection<Long> subtract = CollectionUtils.subtract(idProdutosCompra, idsEncontrados);

		if (subtract.size() != 0) {
			throw new ProdutoNotFoundException("Produto não encontrado: IDs " + subtract);
		}
		return listaProdutosEncontrados;
	}


	public List<Pedido> listarTodos() {
		return repository.listaTodos();
	}
}
