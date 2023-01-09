package br.com.alura.clientelo.core.usecase.categoria;

import br.com.alura.clientelo.core.usecase.exception.CategoriaNotFoundException;
import br.com.alura.clientelo.core.entity.categoria.RepositorioDeCategoria;
import br.com.alura.clientelo.core.usecase.dto.RelatorioVendasPorCategoriaDTO;
import br.com.alura.clientelo.core.entity.categoria.Categoria;
import br.com.alura.clientelo.core.entity.categoria.StatusCategoria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
	private final Logger LOG = LoggerFactory.getLogger(CategoriaService.class);
	@Autowired
	private RepositorioDeCategoria repository;

	@Transactional
	public Categoria cadastrar(Categoria categoria) {
		categoria.setStatus(StatusCategoria.ATIVA);
		repository.cadastrar(categoria);
		return categoria;
	}

	@Transactional
	public Optional<Categoria> atualizaCategoria(Long id, Categoria categoria) {
		if (repository.buscarPorId(id).isEmpty()) {
			return Optional.empty();
		}
		categoria.setId(id);
		return Optional.ofNullable(repository.atualizar(categoria));
	}

	@Transactional(readOnly = true)
	public Categoria buscaPorId(Long id) {
		Optional<Categoria> categoria = repository.buscarPorId(id);
		if (categoria.isEmpty()) {
			throw new CategoriaNotFoundException(String.valueOf(id));
		}
		return categoria.get();
	}

	@Transactional(readOnly = true)
	public Optional<Categoria> buscaPorNome(String nome) {
		return repository.buscarPorNome(nome);
	}

	@Transactional(readOnly = true)
	public List<Categoria> listaTodas() {
		return repository.listarTodas();
	}


	@Transactional(readOnly = true)
	public List<RelatorioVendasPorCategoriaDTO> relatorioVendasPorCategoria() {
		return repository.relatorioVendasPorCategoria();
	}
}
