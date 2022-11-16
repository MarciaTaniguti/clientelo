package br.com.alura.clientelo.dao;

import br.com.alura.clientelo.model.Categoria;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoriaDao {
	private final EntityManager em;

	public CategoriaDao(EntityManager em) {
		this.em = em;
	}

	public Categoria buscaPorId(Long id) {
		return this.em.find(Categoria.class, id);
	}

	public void cadastra(Categoria categoria) {
		this.em.persist(categoria);
	}

	public void atualizaCategoria(Categoria categoria) {
		this.em.merge(categoria);
	}

	public List<Categoria> listaTodas() {
		return em.createQuery("SELECT c FROM Categoria c", Categoria.class).getResultList();
	}

}
