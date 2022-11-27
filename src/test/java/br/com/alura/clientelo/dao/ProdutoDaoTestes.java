package br.com.alura.clientelo.dao;

import br.com.alura.clientelo.orm.Categoria;
import br.com.alura.clientelo.orm.Produto;
import br.com.alura.clientelo.service.CrudProdutoService;
import br.com.alura.clientelo.util.JPAUtil;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
public class ProdutoDaoTestes {
	private final CrudProdutoService service;
	private static Categoria tecnologia;
	private static Categoria decoracao;
	private static Categoria livro;

	@Autowired
	public ProdutoDaoTestes(CrudProdutoService service) {
		this.service = service;
	}

	@Test
	public void deveCadastrarProdutos() {
		Produto notebook = new Produto("Notebook", "Dell X", 3, tecnologia, new BigDecimal("2000"));
		Produto iPhone = new Produto("iPhone 14", null, 10, tecnologia, new BigDecimal("8000"));
		Produto quadro = new Produto("Quadro Marvel", null, 0, decoracao, new BigDecimal("100"));
		Produto cleanArch = new Produto("Clean Architecture", "Livro de tecnologia", 0, livro, new BigDecimal("102.90"));

		service.cadastra(notebook);
		service.cadastra(iPhone);
		service.cadastra(quadro);
		service.cadastra(cleanArch);
	}

	@Test
	public void deveListarProdutosIndisponiveis() {
		Produto amigurumi = new Produto("Amigurumi de cachorro", null, 0, decoracao, new BigDecimal("50"));
		Produto alexa = new Produto("Alexa", "4 geração", 0, tecnologia, new BigDecimal("400"));

		service.cadastra(amigurumi);
		service.cadastra(alexa);

		List<Produto> produtosIndisponiveis = service.listaIndisponiveis();

		Assertions.assertEquals(1, produtosIndisponiveis.stream().filter(produto -> produto.getId().equals(amigurumi.getId())).count());
		Assertions.assertEquals(1, produtosIndisponiveis.stream().filter(produto -> produto.getId().equals(alexa.getId())).count());
	}
}

