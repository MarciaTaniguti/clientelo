package br.com.alura.clientelo;

import br.com.alura.clientelo.dao.*;
import br.com.alura.clientelo.model.*;
import br.com.alura.clientelo.util.JPAUtil;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Arrays;

public class CadastraValoresDummy {

	private EntityManager em = JPAUtil.getEntityManager();
	private CategoriaDao categoriaDao = new CategoriaDao(em);
	private ClienteDao clienteDao = new ClienteDao(em);
	private EnderecoDao enderecoDao = new EnderecoDao(em);
	private PedidoDao pedidoDao = new PedidoDao(em);
	private ProdutoDao produtoDao = new ProdutoDao(em);

	@Test
	public void cadastrar() {
		Categoria alimento = new Categoria("ALIMENTO");
		Categoria tecnologia = new Categoria("TECNOLOGIA");
		Categoria livro = new Categoria("LIVRO");
		Categoria decoracao = new Categoria("DECORAÇÃO");

		Endereco endAna = new Endereco("Rua das Margaridas", "420", null, "Soledade", "Aracaju", "SE");
		Endereco endGiovana = new Endereco("Rua Dom Pedro", "884", "Conjunto E 123", "Samambaia", "Brasília", "DF");
		Endereco endVinicius = new Endereco("Rua Raimundo Oliva Hora", "446", null, "Ampliação", "Itaboraí", "RJ");

		em.getTransaction().begin();
		enderecoDao.cadastra(endAna);
		enderecoDao.cadastra(endGiovana);
		enderecoDao.cadastra(endVinicius);
		em.getTransaction().commit();

		Cliente ana = new Cliente("Ana da Conceição", "29118508154", "79984861807", endAna);
		Cliente giovana = new Cliente("Giovana Marina Sales", "66881157193", "61995903238", endGiovana);
		Cliente vinicius = new Cliente("Vinicius João Miguel Jesus", "84789417034", "21993526814", endVinicius);

		em.getTransaction().begin();
		clienteDao.cadastra(ana);
		clienteDao.cadastra(giovana);
		clienteDao.cadastra(vinicius);
		em.getTransaction().commit();

		Produto notebook = new Produto("Notebook", "Dell X", 3, tecnologia, new BigDecimal("2000"));
		Produto iPhone = new Produto("iPhone 14", null, 10, tecnologia, new BigDecimal("8000"));
		Produto quadro = new Produto("Quadro Marvel", null, 5, decoracao, new BigDecimal("100"));
		Produto cleanArch = new Produto("Clean Architecture", "Livro de tecnologia", 2, livro, new BigDecimal("102.90"));

		em.getTransaction().begin();
		produtoDao.cadastra(notebook);
		produtoDao.cadastra(iPhone);
		produtoDao.cadastra(quadro);
		produtoDao.cadastra(cleanArch);
		em.getTransaction().commit();

		Pedido pedidoAna = new Pedido(ana, new BigDecimal("0"), TipoDescontoPedido.NENHUM,
				Arrays.asList(new ItemPedido(1L, iPhone, new BigDecimal("0.05"), TipoDescontoItemPedido.PROMOCAO)));
		Pedido pedidoGiovana = new Pedido(giovana, new BigDecimal("0.3"), TipoDescontoPedido.FIDELIDADE,
				Arrays.asList(new ItemPedido(1L, iPhone, new BigDecimal("0.05"), TipoDescontoItemPedido.PROMOCAO),
						new ItemPedido(1L, cleanArch, new BigDecimal("0"), TipoDescontoItemPedido.NENHUM),
						new ItemPedido(1L, notebook, new BigDecimal("0.25"), TipoDescontoItemPedido.PROMOCAO)));
		Pedido pedidoVinicius = new Pedido(vinicius, new BigDecimal("0"), TipoDescontoPedido.NENHUM,
				Arrays.asList(new ItemPedido(1L, iPhone, new BigDecimal("0.05"), TipoDescontoItemPedido.PROMOCAO),
						new ItemPedido(3L, quadro, new BigDecimal("0.1"), TipoDescontoItemPedido.PROMOCAO)));

		em.getTransaction().begin();
		pedidoDao.cadastrar(pedidoAna);
		pedidoDao.cadastrar(pedidoGiovana);
		pedidoDao.cadastrar(pedidoVinicius);
		em.getTransaction().commit();
	}
}
