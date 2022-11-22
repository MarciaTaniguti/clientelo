package br.com.alura.clientelo.dao;

import br.com.alura.clientelo.orm.ItemPedido;
import br.com.alura.clientelo.orm.Pedido;
import br.com.alura.clientelo.dto.RelatorioClientesFieisDTO;
import br.com.alura.clientelo.dto.RelatorioProdutosMaisVendidosDTO;
import br.com.alura.clientelo.dto.RelatorioVendasPorCategoriaDTO;

import javax.persistence.EntityManager;
import java.util.List;

public class RelatorioDao {
	private EntityManager em;

	public RelatorioDao(EntityManager em) {
		this.em = em;
	}

	public List<RelatorioClientesFieisDTO> obterClientesFieis() {
		String jpql = "SELECT new " + RelatorioClientesFieisDTO.class.getName() +
				"(c.nome, count(p.id), sum(p.totalGasto)) FROM " +
				Pedido.class.getName() + " p " +
				"JOIN p.cliente c " +
				"JOIN p.itens ip " +
				"GROUP BY c.id " +
				"ORDER BY sum(p.totalGasto) DESC ";
		return em.createQuery(jpql, RelatorioClientesFieisDTO.class)
				.setMaxResults(3)
				.getResultList();
	}

	public List<RelatorioVendasPorCategoriaDTO> obterVendasPorCategoria() {
		String jpql = "SELECT new " + RelatorioVendasPorCategoriaDTO.class.getName() +
				" (c.nome, SUM(ip.quantidade), sum(ip.valorPago)) FROM " +
				ItemPedido.class.getName() + " ip " +
				"JOIN ip.produto p " +
				"RIGHT JOIN p.categoria c " +
				"GROUP BY c.id";
		return em.createQuery(jpql, RelatorioVendasPorCategoriaDTO.class).getResultList();
	}

	public List<RelatorioProdutosMaisVendidosDTO> obterProdutosMaisVendidos() {
		String jpql = "SELECT new " + RelatorioProdutosMaisVendidosDTO.class.getName() +
				" (p.nome, sum(ip.quantidade), p.descricao, p.preco) FROM " +
				ItemPedido.class.getName() + " ip " +
				"JOIN ip.produto p " +
				"GROUP BY p.id " +
				"HAVING sum(ip.quantidade) > 3 " +
				"ORDER BY sum(ip.quantidade) desc";
		return em.createQuery(jpql, RelatorioProdutosMaisVendidosDTO.class)
				.getResultList();
	}
}
