package br.com.alura.clientelo.service;

import br.com.alura.clientelo.api.exception.CategoriaNotFoundException;
import br.com.alura.clientelo.api.form.CadastroProdutoForm;
import br.com.alura.clientelo.api.form.ExibeProdutoForm;
import br.com.alura.clientelo.api.mapper.ProdutoMapper;
import br.com.alura.clientelo.orm.Categoria;
import br.com.alura.clientelo.orm.Produto;
import br.com.alura.clientelo.repository.CategoriaRespository;
import br.com.alura.clientelo.repository.ProdutoRepository;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	public CadastroProdutoForm cadastra(CadastroProdutoForm cadastroProdutoForm) {
		Produto produto = mapper.toModel(cadastroProdutoForm);
		Optional<Categoria> categoria = categoriaRespository.findById(produto.getCategoria().getId());
		if (categoria.isEmpty()) {
			throw new CategoriaNotFoundException();
		}
		produto.setCategoria(categoria.get());
		repository.save(produto);
		return cadastroProdutoForm;
	}

	@Transactional
	public void atualiza(List<Produto> produtos) {
		repository.saveAll(produtos);
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

	public Page<ExibeProdutoForm> listaTodos(Pageable pageable) {
		Page<Produto> pageProduto = repository.findAll(pageable);
		return pageProduto.map(this::converteParaExibeProdutoForm);
	}

	public List<Produto> listaIndisponiveis() {
		return repository.listaIndisponiveis();
	}

	private ExibeProdutoForm converteParaExibeProdutoForm(Produto produto) {
		return new ExibeProdutoForm(produto.getNome(), produto.getPreco(), produto.getDescricao(),
				produto.getQuantidadeEstoque(), produto.getCategoria().getId(), produto.getCategoria().getNome());
	}
}
