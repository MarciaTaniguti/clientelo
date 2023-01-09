package br.com.alura.clientelo.core.usecase.produto;

import br.com.alura.clientelo.core.usecase.exception.CategoriaNotFoundException;
import br.com.alura.clientelo.core.usecase.exception.ProdutoNotFoundException;
import br.com.alura.clientelo.core.entity.categoria.RepositorioDeCategoria;
import br.com.alura.clientelo.core.entity.produto.RepositorioDeProduto;
import br.com.alura.clientelo.core.usecase.dto.ExibeProdutoDto;
import br.com.alura.clientelo.core.entity.categoria.Categoria;
import br.com.alura.clientelo.core.entity.produto.Produto;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
	@Autowired
	private RepositorioDeProduto repository;
	@Autowired
	private RepositorioDeCategoria categoriaRespository;

	public Produto buscaPorId(Long id) {
		Optional<Produto> produto = repository.buscaPorId(id);
		if (produto.isEmpty()) {
			throw new ProdutoNotFoundException("ID = " + id);
		}
		return produto.get();
	}

	@Transactional
	public Produto cadastra(Produto produto) {
		Long idCategoria = produto.getCategoria().getId();
		Optional<Categoria> categoria = categoriaRespository.buscarPorId(idCategoria);
		if (categoria.isEmpty()) {
			throw new CategoriaNotFoundException(String.valueOf(idCategoria));
		}
		produto.setCategoria(categoria.get());
		repository.cadastrar(produto);
		return produto;
	}

	@Transactional
	public void atualiza(List<Produto> produtos) {
		repository.atualizar(produtos);
	}

	public boolean temEstoque(Long id, Long qtd) {
		return repository.temEstoque(id, qtd).intValue() > 0;
	}

	public List<Produto> listaTodos(List<Long> ids) {
		return IterableUtils.toList(repository.buscaPorId(ids));
	}

	public Page<ExibeProdutoDto> listaTodos(Pageable pageable) {
		Page<Produto> pageProduto = repository.buscaTodos(pageable);
		return pageProduto.map(this::toDto);
	}

	public List<Produto> listaIndisponiveis() {
		return repository.listaIndisponiveis();
	}

	private ExibeProdutoDto toDto(Produto produto) {
		return new ExibeProdutoDto(produto.getNome(), produto.getPreco(), produto.getDescricao(),
				produto.getQuantidadeEstoque(), produto.getCategoria().getId(), produto.getCategoria().getNome());
	}
}
