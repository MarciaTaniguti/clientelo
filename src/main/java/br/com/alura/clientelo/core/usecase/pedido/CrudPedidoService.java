package br.com.alura.clientelo.core.usecase.pedido;

import br.com.alura.clientelo.api.exception.ClienteNotFoundException;
import br.com.alura.clientelo.api.exception.PedidoNotFoundException;
import br.com.alura.clientelo.api.exception.ProdutoNotFoundException;
import br.com.alura.clientelo.api.exception.ProdutoSemEstoqueException;
import br.com.alura.clientelo.api.form.DetalhePedidoForm;
import br.com.alura.clientelo.api.form.ItemPedidoForm;
import br.com.alura.clientelo.api.form.PedidoForm;
import br.com.alura.clientelo.core.usecase.cliente.ClienteService;
import br.com.alura.clientelo.core.usecase.produto.CrudProdutoService;
import br.com.alura.clientelo.core.usecase.dto.PedidoDto;
import br.com.alura.clientelo.core.entity.cliente.Cliente;
import br.com.alura.clientelo.core.entity.pedido.item.ItemPedido;
import br.com.alura.clientelo.core.entity.pedido.Pedido;
import br.com.alura.clientelo.core.entity.produto.Produto;
import br.com.alura.clientelo.repository.PedidoRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class CrudPedidoService {
	@Autowired
	private PedidoRepository repository;
	@Autowired
	private CrudItemPedidoService itemPedidoService;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private CrudProdutoService produtoService;
	@Autowired
	private PedidoMapper mapper;

	@Transactional
	public PedidoDto cadastrar(PedidoForm pedidoForm) {
		List<ItemPedido> itemPedidos = new ArrayList<>();

		Cliente cliente = recuperarClienteDaBase(pedidoForm.getIdCliente());
		ordenarListaPedidoById(pedidoForm);
		List<Produto> listaProdutosEncontrados = recuperarPedidosDaBase(pedidoForm);
		validaEstoqueDosProdutos(pedidoForm.getItens(), listaProdutosEncontrados, itemPedidos);
		
		Pedido pedido = new Pedido(cliente, itemPedidos);
		repository.save(pedido);
		produtoService.atualiza(listaProdutosEncontrados);

		return mapper.toDto(pedido);
	}

	@Transactional
	public PedidoDto cadastrar(Pedido pedido) {
		repository.save(pedido);
		return mapper.toDto(pedido);
	}

	public Long quantidadePedidosPorCliente(Long clienteId) {
		return repository.quantidadePedidoPorCliente(clienteId);
	}

	public DetalhePedidoForm buscarDetalhePedido(Long id) {
		Optional<Pedido> pedido = repository.findById(id);
		if (pedido.isEmpty()) {
			throw new PedidoNotFoundException("Pedido não encontrado!");
		}
		DetalhePedidoForm detalhePedidoForm = mapper.toDetalhePedidoForm(pedido.get());  //TODO - refactor
		detalhePedidoForm.setDescontos(pedido.get().getValorDesconto());

		return detalhePedidoForm;
	}

	private void validaEstoqueDosProdutos(List<ItemPedidoForm> itens, List<Produto> listaProdutosEncontrados, List<ItemPedido> itemPedidos) {
		for(int i = 0; i<itens.size(); i++) {
			Long quantidadeEstoque = listaProdutosEncontrados.get(i).getQuantidadeEstoque();
			Long quantidadePedido = itens.get(i).quantidade();
			long quantidadeRestante = quantidadeEstoque - quantidadePedido;
			if (quantidadeRestante < 0) {
				throw new ProdutoSemEstoqueException("Produto ID " + listaProdutosEncontrados.get(i).getId() + " - em estoque: " + quantidadeEstoque);
			}
			itemPedidos.add(new ItemPedido(quantidadePedido, listaProdutosEncontrados.get(i)));
			listaProdutosEncontrados.get(i).setQuantidadeEstoque(quantidadeRestante);
		}
	}

	public List<PedidoDto> listarTodos() {
		return mapper.toDto(IterableUtils.toList(repository.findAll()));
	}

	private void ordenarListaPedidoById(PedidoForm pedido) {
		List<ItemPedidoForm> listaOrdenadaItemPedidos = pedido.getItens().stream().sorted(Comparator.comparingLong(ItemPedidoForm::id)).toList();
		pedido.replaceItens(listaOrdenadaItemPedidos);
	}

	private Cliente recuperarClienteDaBase(Long idCliente) {
		return clienteService.buscaPorId(idCliente).toModel();
	}

	private List<Produto> recuperarPedidosDaBase(PedidoForm pedido) {
		List<Long> idProdutosCompra = pedido.getItens().stream().map(ItemPedidoForm::id).toList();
		List<Produto> listaProdutosEncontrados = produtoService.listaTodos(idProdutosCompra);
		List<Long> idsEncontrados = listaProdutosEncontrados.stream().map(Produto::getId).toList();

		Collection<Long> subtract = CollectionUtils.subtract(idProdutosCompra, idsEncontrados);

		if (subtract.size() != 0) {
			throw new ProdutoNotFoundException("Produto não encontrado: IDs " + subtract);
		}
		return listaProdutosEncontrados;
	}
}
