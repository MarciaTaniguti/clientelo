package br.com.alura.clientelo.service;

import br.com.alura.clientelo.api.form.CategoriaForm;
import br.com.alura.clientelo.api.mapper.CategoriaMapper;
import br.com.alura.clientelo.orm.Categoria;
import br.com.alura.clientelo.orm.StatusCategoria;
import br.com.alura.clientelo.repository.CategoriaRespository;
import org.apache.commons.collections4.IterableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CrudCategoriaService {
	private final CategoriaRespository repository;
	private final CategoriaMapper mapper;
	private final Logger LOG = LoggerFactory.getLogger(CrudCategoriaService.class);



	@Autowired
	public CrudCategoriaService(CategoriaRespository repository, CategoriaMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	public CategoriaForm cadastra(CategoriaForm categoriaForm) {
		Categoria categoria = mapper.toModel(categoriaForm);
		categoria.setStatus(StatusCategoria.ATIVA);
		repository.save(categoria);
		return mapper.toDto(categoria);
	}

	public void atualizaCategoria(Long id, CategoriaForm categoria) {
		Optional<Categoria> categoriaEncontrada = repository.findById(id);

		if (categoriaEncontrada.isEmpty()) {
			LOG.info("Categoria não encontrada!");
			return;
		}

		repository.save(mapper.toModel(categoria));
		LOG.info("ATUALIZADO!");
	}

	public List<Categoria> listaTodas() {
		return IterableUtils.toList(repository.findAll());
	}

	public Optional<Categoria> buscaPorId(Long id) {
		return repository.findById(id);
	}

	public Optional<Categoria> buscaPorNome(String nome) {
		List<Categoria> listaCategoria = repository.findByNome(nome);
		if (!listaCategoria.isEmpty()) {
			return Optional.ofNullable(repository.findByNome(nome).get(0));
		}
		return Optional.empty();
	}

}
