package br.com.alura.clientelo.dao;

import br.com.alura.clientelo.core.entity.cliente.Cliente;
import br.com.alura.clientelo.service.CrudClienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ClienteDaoTestes {

	private final CrudClienteService service;
	private static final Logger LOG = LoggerFactory.getLogger(ClienteDaoTestes.class);

	@Autowired
	public ClienteDaoTestes(CrudClienteService service) {
		this.service = service;
	}

	@Test
	public void devePesquisarPeloNomeDoCliente() {
		List<Cliente> cliente = service.buscaPorNome("Ana da Conceição");

		Assertions.assertTrue(cliente.size() > 0);
		Assertions.assertEquals("Ana da Conceição", cliente.get(0).getNome());
	}

	@Test
	public void deveCadastrarEListarClientes() {
		List<Cliente> clientes = service.listaTodos();

		Assertions.assertEquals(1, clientes.stream().filter(cliente -> cliente.getNome()
				.equals("Ana da Conceição")).count());
		Assertions.assertEquals(1, clientes.stream().filter(cliente -> cliente.getNome()
				.equals("Giovana Marina Sales")).count());
		Assertions.assertEquals(1, clientes.stream().filter(cliente -> cliente.getNome()
				.equals("Vinicius João Miguel Jesus")).count());

	}


}
