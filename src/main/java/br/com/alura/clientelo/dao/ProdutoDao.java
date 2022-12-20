package br.com.alura.clientelo.dao;

import br.com.alura.clientelo.core.entity.produto.Produto;

import javax.persistence.EntityManager;
import java.util.List;

public class ProdutoDao {
	private final EntityManager em;
	private static final String PRODUTO = Produto.class.getName();

	public ProdutoDao(EntityManager em) {
		this.em = em;
	}

	public Produto buscaPorId(String id) {
		return em.find(Produto.class, id);
	}

	public void cadastra(Produto produto) {
		this.em.persist(produto);
	}

	public List<Produto> listaTodos() {
		String jpql = "SELECT p FROM " +
				PRODUTO + " p ";
		return em.createQuery(jpql, Produto.class)
				.getResultList();
	}

	public List<Produto> listaIndisponiveis() {
		String jpql = "SELECT p FROM " +
				PRODUTO +
				" p WHERE p.quantidadeEstoque = :qtd";
		return em.createQuery(jpql, Produto.class)
				.setParameter("qtd", 0)
				.getResultList();
	}

}
