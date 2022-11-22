package br.com.alura.clientelo.dao;

import br.com.alura.clientelo.orm.Categoria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

public class CategoriaDao {  //criar uma interface que tenha o contrato basico dos daos, CRUD
	private EntityManager em;
	private static final String CATEGORIA = Categoria.class.getName();
	private static final Logger LOG = LoggerFactory.getLogger(CategoriaDao.class);

	@Deprecated()
	public CategoriaDao() {
	}

	public CategoriaDao(EntityManager em) {
		this.em = em;
	}

	public Categoria buscaPorId(Long id) {
		return this.em.find(Categoria.class, id);
	}

	public void cadastra(Categoria categoria) {
		this.em.persist(categoria);
	}

	public Optional<Categoria> buscaPorNome(String nome) {
		//begin
		//commit
		String jpql = "SELECT c FROM " +
				CATEGORIA + " c where c.nome = :nome";
		Optional<Categoria> categoria = Optional.empty();
		try {
			return Optional.of(em.createQuery(jpql, Categoria.class)
					.setParameter("nome", nome)
					.getSingleResult());
		}
		catch (NoResultException exp) {
			return categoria;
		}
	}

	public void atualizaCategoria(Categoria categoria) {
		this.em.merge(categoria);
	}

	public List<Categoria> listaTodas() {
		return em.createQuery("SELECT c FROM Categoria c", Categoria.class).getResultList();
	}

}
