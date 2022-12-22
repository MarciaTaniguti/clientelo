package br.com.alura.clientelo.infra.jpa.produto;

import br.com.alura.clientelo.core.entity.produto.Produto;
import br.com.alura.clientelo.core.entity.produto.RepositorioDeProduto;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public class ProdutoRepositorySpringData implements RepositorioDeProduto {
	@Autowired
	private ProdutoRepositoryJPA repository;

	@Override
	public Produto cadastrar(Produto produto) {
		return repository.save(produto);
	}

	@Override
	public void atualizar(List<Produto> produtos) {
		repository.saveAll(produtos);
	}

	@Override
	public Optional<Produto> buscaPorId(Long id) {
		return repository.findById(id);
	}

	@Override
	public List<Produto> buscaPorId(List<Long> ids) {
		return IterableUtils.toList(repository.findAllById(ids));
	}

	@Override
	public Page<Produto> buscaTodos(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public List<Produto> listaIndisponiveis() {
		return repository.listaIndisponiveis();
	}

	@Override
	public BigInteger temEstoque(Long id, Long qtdCompra) {
		return repository.temEstoque(id, qtdCompra);
	}
}
