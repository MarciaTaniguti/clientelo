package br.com.alura.clientelo.service;

import br.com.alura.clientelo.api.form.ProdutoForm;
import br.com.alura.clientelo.api.exception.CategoriaNotFoundException;
import br.com.alura.clientelo.api.mapper.ProdutoMapper;
import br.com.alura.clientelo.orm.Categoria;
import br.com.alura.clientelo.orm.Pedido;
import br.com.alura.clientelo.orm.Produto;
import br.com.alura.clientelo.repository.CategoriaRespository;
import br.com.alura.clientelo.repository.ProdutoRepository;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CrudProdutoService {
	@Autowired
	private ProdutoRepository repository;
	@Autowired
	private CategoriaRespository categoriaRespository;
	@Autowired
	private ProdutoMapper mapper;

	public Optional<Produto> buscaPorId(Long id) {
		return repository.findById(id);
	}

	@Transactional
	public ProdutoForm cadastra(ProdutoForm produtoForm) {
		Produto produto = mapper.toModel(produtoForm);
		Optional<Categoria> categoria = categoriaRespository.findById(produto.getCategoria().getId());
		if (categoria.isEmpty()) {
			throw new CategoriaNotFoundException();
		}
		produto.setCategoria(categoria.get());
		repository.save(produto);
		return produtoForm;
	}

	public boolean checkTodosIdsCadastrados(List<Long> ids) {
		return repository.qtdIdsEncontrados(ids) == ids.size();
	}

	public boolean temEstoque(Long id, Long qtd) {
		return repository.temEstoque(id, qtd).intValue() > 0;
	}

	public List<Produto> listaTodos(List<Long> ids) {
		return IterableUtils.toList(repository.findAllById(ids));
	}

	public List<Produto> listaTodos() {
		return IterableUtils.toList(repository.findAll());
	}

	public List<Produto> listaIndisponiveis() {
		return repository.listaIndisponiveis();
	}
}
