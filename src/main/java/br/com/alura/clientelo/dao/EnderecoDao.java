package br.com.alura.clientelo.dao;

import br.com.alura.clientelo.orm.Endereco;

import javax.persistence.EntityManager;

public class EnderecoDao {
	private final EntityManager em;

	public EnderecoDao(EntityManager em) {
		this.em = em;
	}

	public void cadastra(Endereco endereco) {
		this.em.persist(endereco);
	}
}
