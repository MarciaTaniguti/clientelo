package br.com.alura.clientelo;

import br.com.alura.clientelo.dao.*;
import br.com.alura.clientelo.orm.*;
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

	private static Categoria alimento = new Categoria("ALIMENTO");
	private static Categoria tecnologia = new Categoria("TECNOLOGIA");;
	private static Categoria livro = new Categoria("LIVRO");;
	private static Categoria decoracao = new Categoria("DECORAÇÃO");;
	private static Endereco endAna = new Endereco("Rua das Margaridas", "420", null, "Soledade", "Aracaju", "SE");
	private static Endereco endGiovana = new Endereco("Rua Dom Pedro", "884", "Conjunto E 123", "Samambaia", "Brasília", "DF");
	private static Endereco endVinicius = new Endereco("Rua Raimundo Oliva Hora", "446", null, "Ampliação", "Itaboraí", "RJ");
	private static Cliente ana = new Cliente("Ana da Conceição", "29118508154", "79984861807", endAna);
	private static Cliente giovana = new Cliente("Giovana Marina Sales", "66881157193", "61995903238", endGiovana);
	private static Cliente vinicius = new Cliente("Vinicius João Miguel Jesus", "84789417034", "21993526814", endVinicius);
	private static Produto notebook = new Produto("Notebook", "Dell X", 3L, tecnologia, new BigDecimal("2000"));
	private static Produto iPhone = new Produto("iPhone 14", null, 10L, tecnologia, new BigDecimal("8000"));
	private static Produto quadro = new Produto("Quadro Marvel", null, 5L, decoracao, new BigDecimal("100"));
	private static Produto cleanArch = new Produto("Clean Architecture", "Livro de tecnologia", 2L, livro, new BigDecimal("102.90"));

//	private static Pedido pedidoAna = new Pedido(ana, new BigDecimal("0"), TipoDescontoPedido.NENHUM,Arrays.asList(
//			new ItemPedido(1L, iPhone, new BigDecimal("0.05"), TipoDescontoItemPedido.PROMOCAO)));
//	private static Pedido pedidoGiovana = new Pedido(giovana, new BigDecimal("0.3"), TipoDescontoPedido.FIDELIDADE,Arrays.asList(
//			new ItemPedido(2L, iPhone, new BigDecimal("0.1"), TipoDescontoItemPedido.PROMOCAO),
//			new ItemPedido(1L, cleanArch, new BigDecimal("0"), TipoDescontoItemPedido.NENHUM),
//			new ItemPedido(1L, notebook, new BigDecimal("0.25"), TipoDescontoItemPedido.PROMOCAO)));
//	private static Pedido pedidoVinicius = new Pedido(vinicius, new BigDecimal("0"), TipoDescontoPedido.NENHUM, Arrays.asList(
//			new ItemPedido(1L, iPhone, new BigDecimal("0.05"), TipoDescontoItemPedido.PROMOCAO),
//			new ItemPedido(3L, quadro, new BigDecimal("0.1"), TipoDescontoItemPedido.PROMOCAO)));

	@Test
	public void cadastrar() {
		em.getTransaction().begin();
		enderecoDao.cadastra(endAna);
		enderecoDao.cadastra(endGiovana);
		enderecoDao.cadastra(endVinicius);

		clienteDao.cadastra(ana);
		clienteDao.cadastra(giovana);
		clienteDao.cadastra(vinicius);

		produtoDao.cadastra(notebook);
		produtoDao.cadastra(iPhone);
		produtoDao.cadastra(quadro);
		produtoDao.cadastra(cleanArch);

//		pedidoDao.cadastrar(pedidoAna);
//		pedidoDao.cadastrar(pedidoGiovana);
//		pedidoDao.cadastrar(pedidoVinicius);

		em.getTransaction().commit();
	}

	public static Categoria getAlimento() {
		return alimento;
	}

	public static Categoria getTecnologia() {
		return tecnologia;
	}

	public static Categoria getLivro() {
		return livro;
	}

	public static Categoria getDecoracao() {
		return decoracao;
	}

	public static Endereco getEndAna() {
		return endAna;
	}

	public static Endereco getEndGiovana() {
		return endGiovana;
	}

	public static Endereco getEndVinicius() {
		return endVinicius;
	}

	public static Cliente getAna() {
		return ana;
	}

	public static Cliente getGiovana() {
		return giovana;
	}

	public static Cliente getVinicius() {
		return vinicius;
	}

	public static Produto getNotebook() {
		return notebook;
	}

	public static Produto getiPhone() {
		return iPhone;
	}

	public static Produto getQuadro() {
		return quadro;
	}

	public static Produto getCleanArch() {
		return cleanArch;
	}

//	public static Pedido getPedidoAna() {
//		return pedidoAna;
//	}
//
//	public static Pedido getPedidoGiovana() {
//		return pedidoGiovana;
//	}
//
//	public static Pedido getPedidoVinicius() {
//		return pedidoVinicius;
//	}
}
