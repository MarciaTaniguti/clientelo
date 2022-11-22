package br.com.alura.clientelo.dao;

import br.com.alura.clientelo.orm.Cliente;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class ClienteDao {
	private final EntityManager em;
	private static final String CLIENTE = Cliente.class.getName();

	public ClienteDao(EntityManager em) {
		this.em = em;
	}

	public Cliente buscaPorId(Long id) {
		return em.find(Cliente.class, id);
	}

	public void cadastra(Cliente cliente) {
		this.em.persist(cliente);
	}

	public void atualiza(Cliente cliente) {
		this.em.merge(cliente);
	}

	public void remove(Cliente cliente) {
		cliente = this.em.merge(cliente);
		this.em.remove(cliente);
	}

	public List<Cliente> listaTodos() {
		String jpql = "SELECT c FROM " + CLIENTE + " c";
		return em.createQuery(jpql, Cliente.class).getResultList();
	}

	public Optional<Cliente> buscaPorNome(String nome) {
		String jpql = "SELECT c FROM " +
				CLIENTE +
				" c WHERE c.nome = :nome";
		return Optional.ofNullable(em.createQuery(jpql, Cliente.class)
				.setParameter("nome", nome)
				.getSingleResult());
	}

}
