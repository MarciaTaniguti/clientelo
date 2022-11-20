package br.com.alura.clientelo.dao;

import br.com.alura.clientelo.model.*;
import br.com.alura.clientelo.util.JPAUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ClienteDaoTestes {
	private static EntityManager em = JPAUtil.getEntityManager();
	private static ClienteDao clienteDao = new ClienteDao(em);
	private static EnderecoDao enderecoDao = new EnderecoDao(em);
	private static final Logger LOG = LoggerFactory.getLogger(ClienteDaoTestes.class);

	@AfterAll
	public static void close() {
		em.close();
	}

	@BeforeAll
	public static void init() {
		em.getTransaction().begin();

		Endereco endAna = new Endereco("Rua das Margaridas", "420", null, "Soledade", "Aracaju", "SE");
		Endereco endGiovana = new Endereco("Rua Dom Pedro", "884", "Conjunto E 123", "Samambaia", "Brasília", "DF");
		Endereco endVinicius = new Endereco("Rua Raimundo Oliva Hora", "446", null, "Ampliação", "Itaboraí", "RJ");

		Cliente ana = new Cliente("Ana da Conceição", "29118508154", "79984861807", endAna);
		Cliente giovana = new Cliente("Giovana Marina Sales", "66881157193", "61995903238", endGiovana);
		Cliente vinicius = new Cliente("Vinicius João Miguel Jesus", "84789417034", "21993526814", endVinicius);

		enderecoDao.cadastra(endAna);
		clienteDao.cadastra(ana);

		enderecoDao.cadastra(endGiovana);
		clienteDao.cadastra(giovana);

		enderecoDao.cadastra(endVinicius);
		clienteDao.cadastra(vinicius);

		em.getTransaction().commit();
	}


	@Test
	public void devePesquisarPeloNomeDoCliente() {
		Optional<Cliente> cliente = clienteDao.buscaPorNome("Ana da Conceição");

		Assertions.assertTrue(cliente.isPresent());
		Assertions.assertEquals("Ana da Conceição", cliente.get().getNome());
		LOG.info(cliente.get().getNome());
	}

	@Test
	public void deveCadastrarEListarClientes() {
		List<Cliente> clientes = clienteDao.listaTodos();

		Assertions.assertEquals(1, clientes.stream().filter(cliente -> cliente.getNome()
				.equals("Ana da Conceição")).count());
		Assertions.assertEquals(1, clientes.stream().filter(cliente -> cliente.getNome()
				.equals("Giovana Marina Sales")).count());
		Assertions.assertEquals(1, clientes.stream().filter(cliente -> cliente.getNome()
				.equals("Vinicius João Miguel Jesus")).count());

	}


}
