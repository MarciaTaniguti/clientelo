package br.com.alura.clientelo.dao;

import br.com.alura.clientelo.model.Categoria;
import br.com.alura.clientelo.model.StatusCategoria;
import br.com.alura.clientelo.util.JPAUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class CategoriaDaoTestes {

	private static EntityManager em = JPAUtil.getEntityManager();
	private static CategoriaDao categoriaDao = new CategoriaDao(em);

	@BeforeEach
	public void begin() {
		em.getTransaction().begin();
	}

	@AfterAll
	public static void close() {
		em.close();
	}

	@Test
	public void deveCadastrarEListarCategorias() {
		Categoria alimento = new Categoria("ALIMENTO");
		Categoria tecnologia = new Categoria("TECNOLOGIA");
		Categoria livro = new Categoria("LIVRO");

		categoriaDao.cadastra(alimento);
		categoriaDao.cadastra(tecnologia);
		categoriaDao.cadastra(livro);

		em.getTransaction().commit();

		List<Categoria> categorias = categoriaDao.listaTodas();

		Assertions.assertEquals(1, categorias.stream().filter(categoria -> categoria.getNome()
				.equals(alimento.getNome())).count());
		Assertions.assertEquals(1, categorias.stream().filter(categoria -> categoria.getNome()
				.equals(tecnologia.getNome())).count());
		Assertions.assertEquals(1, categorias.stream().filter(categoria -> categoria.getNome()
				.equals(livro.getNome())).count());
	}

	@Test
	public void deveAtualizarNomeCategoria() {
		Categoria categoria = new Categoria("pet");

		categoriaDao.cadastra(categoria);
		em.flush();

		categoria.setNome("PET");
		categoriaDao.atualizaCategoria(categoria);
		em.getTransaction().commit();

		Assertions.assertEquals(categoria.getNome(), categoriaDao.buscaPorId(categoria.getId()).getNome());
	}

	@Test
	public void deveAtualizarStatusCategoria() {
		Categoria categoria = new Categoria("PET");
		Optional<Categoria> buscaCategoriaPet = categoriaDao.buscaPorNome(categoria.getNome());
		if (buscaCategoriaPet.isEmpty()) {
			categoriaDao.cadastra(categoria);
			em.flush();
		} else {
			categoria = buscaCategoriaPet.get();
		}
		categoria.setStatus(StatusCategoria.INATIVA);
		categoriaDao.atualizaCategoria(categoria);
		em.getTransaction().commit();

		Assertions.assertEquals(categoria.getStatus().name(), categoriaDao.buscaPorId(categoria.getId()).getStatus().name());
	}



}
