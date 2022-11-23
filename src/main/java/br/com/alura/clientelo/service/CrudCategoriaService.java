package br.com.alura.clientelo.service;

import br.com.alura.clientelo.orm.Categoria;
import br.com.alura.clientelo.repository.CategoriaRespository;
import org.apache.commons.collections4.IterableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CrudCategoriaService {
	private final CategoriaRespository repository;
	private final Logger LOG = LoggerFactory.getLogger(CrudCategoriaService.class);

	public CrudCategoriaService(CategoriaRespository repository) {
		this.repository = repository;
	}

	public void cadastrar(Categoria categoria) {
		repository.save(categoria);
	}

	public void atualizaCategoria(Categoria categoria) {
		Optional<Categoria> categoriaEncontrada = repository.findById(categoria.getId());

		if (categoriaEncontrada.isEmpty()) {
			LOG.info("Categoria não encontrada!");
			return;
		}

		repository.save(categoria);
		LOG.info("ATUALIZADO!");
	}

	public List<Categoria> listaTodas() {
		return IterableUtils.toList(repository.findAll());
	}

	public Optional<Categoria> buscaPorId(Long id) {
		return repository.findById(id);
	}

	public List<Categoria> buscaPorNome(String nome) {
		return repository.findByNome(nome);
	}

}
