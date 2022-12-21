package br.com.alura.clientelo.core.usecase.produto;

import br.com.alura.clientelo.api.exception.CategoriaNotFoundException;
import br.com.alura.clientelo.api.exception.ProdutoNotFoundException;
import br.com.alura.clientelo.api.form.CadastroProdutoForm;
import br.com.alura.clientelo.core.entity.categoria.RepositorioDeCategoria;
import br.com.alura.clientelo.core.usecase.dto.ExibeProdutoDto;
import br.com.alura.clientelo.core.usecase.dto.ProdutoDto;
import br.com.alura.clientelo.core.entity.categoria.Categoria;
import br.com.alura.clientelo.core.entity.produto.Produto;
import br.com.alura.clientelo.repository.ProdutoRepository;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
	private RepositorioDeCategoria categoriaRespository;
	@Autowired
	private ProdutoMapper mapper;

	public ProdutoDto buscaPorId(Long id) {
		Optional<Produto> produto = repository.findById(id);
		if (produto.isEmpty()) {
			throw new ProdutoNotFoundException("ID = " + id);
		}
		return mapper.toDto(produto.get());
	}
	
	public ProdutoDto cadastra(CadastroProdutoForm cadastroProdutoForm) {
		Produto produto = mapper.toModel(cadastroProdutoForm);
		return cadastra(produto);
	}

	@Transactional
	public ProdutoDto cadastra(Produto produto) {
		Long idCategoria = produto.getCategoria().getId();
		Optional<Categoria> categoria = categoriaRespository.buscarPorId(idCategoria);
		if (categoria.isEmpty()) {
			throw new CategoriaNotFoundException(String.valueOf(idCategoria));
		}
		produto.setCategoria(categoria.get());
		repository.save(produto);
		return mapper.toDto(produto);
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

	public Page<ExibeProdutoDto> listaTodos(Pageable pageable) {
		Page<Produto> pageProduto = repository.findAll(pageable);
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
