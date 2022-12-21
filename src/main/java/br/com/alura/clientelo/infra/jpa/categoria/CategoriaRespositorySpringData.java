package br.com.alura.clientelo.infra.jpa.categoria;

import br.com.alura.clientelo.core.entity.categoria.Categoria;
import br.com.alura.clientelo.core.entity.categoria.RepositorioDeCategoria;
import br.com.alura.clientelo.core.usecase.dto.RelatorioVendasPorCategoriaDTO;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class CategoriaRespositorySpringData implements RepositorioDeCategoria {

	@Autowired
	private CategoriaRepositoryJPA repository;

	@Override
	public Optional<Categoria> buscarPorId(Long id) {
		return repository.findById(id);
	}

	@Override
	public Optional<Categoria> buscarPorNome(String nome) {
		return repository.findByNome(nome);
	}

	@Override
	public Categoria cadastrar(Categoria categoria) {
		return repository.save(categoria);
	}

	@Override
	public Categoria atualizar(Categoria categoria) {
		return repository.save(categoria);
	}

	@Override
	public List<Categoria> listarTodas() {
		return IterableUtils.toList(repository.findAll());
	}

	@Override
	public List<RelatorioVendasPorCategoriaDTO> relatorioVendasPorCategoria() {
		return repository.relatorioVendasPorCategoria();
	}
}
