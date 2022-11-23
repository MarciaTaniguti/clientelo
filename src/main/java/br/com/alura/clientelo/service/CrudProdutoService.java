package br.com.alura.clientelo.service;

import br.com.alura.clientelo.orm.Produto;
import br.com.alura.clientelo.repository.ProdutoRepository;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CrudProdutoService {
	private final ProdutoRepository repository;

	public CrudProdutoService(ProdutoRepository repository) {
		this.repository = repository;
	}

	public Optional<Produto> buscaPorId(Long id) {
		return repository.findById(id);
	}

	public void cadastra(Produto produto) {
		repository.save(produto);
	}

	public List<Produto> listaTodos() {
		return IterableUtils.toList(repository.findAll());
	}

	public List<Produto> listaIndisponiveis() {
		return repository.listaIndisponiveis();
	}
}
