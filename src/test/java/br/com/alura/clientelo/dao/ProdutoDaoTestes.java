package br.com.alura.clientelo.dao;

import br.com.alura.clientelo.model.Categoria;
import br.com.alura.clientelo.model.Cliente;
import br.com.alura.clientelo.model.Endereco;
import br.com.alura.clientelo.model.Produto;
import br.com.alura.clientelo.util.JPAUtil;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ProdutoDaoTestes {
	private static EntityManager em = JPAUtil.getEntityManager();
	private static CategoriaDao categoriaDao = new CategoriaDao(em);
	private static ProdutoDao produtoDao = new ProdutoDao(em);
	private static Categoria tecnologia;
	private static Categoria decoracao;
	private static Categoria livro;


	@BeforeEach
	public void begin() {
		em.getTransaction().begin();
	}

	@AfterAll
	public static void close() {
		em.close();
	}

	@BeforeAll
	public static void cadastrarCategorias() {
		em.getTransaction().begin();

		tecnologia = new Categoria("TECNOLOGIA");
		decoracao = new Categoria("DECORAÇÃO");
		livro = new Categoria("LIVRO");

		categoriaDao.cadastra(tecnologia);
		categoriaDao.cadastra(decoracao);
		categoriaDao.cadastra(livro);

		em.getTransaction().commit();
	}

	@Test
	public void deveCadastrarProdutos() {
		Produto notebook = new Produto("Notebook", "Dell X", 3, tecnologia, new BigDecimal("2000"));
		Produto iPhone = new Produto("iPhone 14", null, 10, tecnologia, new BigDecimal("8000"));
		Produto quadro = new Produto("Quadro Marvel", null, 0, decoracao, new BigDecimal("100"));
		Produto cleanArch = new Produto("Clean Architecture", "Livro de tecnologia", 0, livro, new BigDecimal("102.90"));

		produtoDao.cadastra(notebook);
		produtoDao.cadastra(iPhone);
		produtoDao.cadastra(quadro);
		produtoDao.cadastra(cleanArch);

		em.getTransaction().commit();
	}

	@Test
	public void deveListarProdutosIndisponiveis() {
		Produto amigurumi = new Produto("Amigurumi de cachorro", null, 0, decoracao, new BigDecimal("50"));
		Produto alexa = new Produto("Alexa", "4 geração", 0, tecnologia, new BigDecimal("400"));

		produtoDao.cadastra(amigurumi);
		produtoDao.cadastra(alexa);

		em.getTransaction().commit();

		List<Produto> produtosIndisponiveis = produtoDao.listaIndisponiveis();

		Assertions.assertEquals(1, produtosIndisponiveis.stream().filter(produto -> produto.getId().equals(amigurumi.getId())).count());
		Assertions.assertEquals(1, produtosIndisponiveis.stream().filter(produto -> produto.getId().equals(alexa.getId())).count());
	}
}

