package br.com.alura.clientelo.dao;

import br.com.alura.clientelo.model.Pedido;

import javax.persistence.EntityManager;

public class PedidoDao {
	private final EntityManager em;

	public PedidoDao(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Pedido pedido) {
		this.em.persist(pedido);
	}
}
