package br.com.alura.clientelo.controller;

import br.com.alura.clientelo.api.controller.ClienteController;
import br.com.alura.clientelo.api.controller.PedidoController;
import br.com.alura.clientelo.api.controller.ProdutoController;
import br.com.alura.clientelo.api.form.ClienteForm;
import br.com.alura.clientelo.api.form.ItemPedidoForm;
import br.com.alura.clientelo.api.form.PedidoForm;
import br.com.alura.clientelo.orm.*;
import br.com.alura.clientelo.relatorio.vendaporcategoria.Item;
import br.com.alura.clientelo.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.beans.Transient;
import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PedidoControllerTest {
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private CrudProdutoService produtoService;
	@Autowired
	private PedidoController pedidoController;
	@Autowired
	private CrudClienteService clienteService;
	@Autowired
	private CrudPedidoService pedidoService;
	@Autowired
	private CrudEnderecoService enderecoService;
	@Autowired
	private CrudCategoriaService categoriaService;
	@Autowired
	private PedidoRequestBuilder builder;

	@BeforeAll
	public void setUp() {
		Categoria categoria = new Categoria(1L,"Decoração");
		categoriaService.cadastra(categoria);

		Endereco endereco = new Endereco("Rua Antônio Arenso","500",null,"Jardim Santa Edwiges","São Paulo","SP");
		enderecoService.cadastrar(endereco);

		Cliente aline = new Cliente("Aline Almeida", "66337035038", "11111111111", endereco);
		clienteService.cadastrar(aline);

		Produto produto = new Produto("Porta-copo", "Resinado na cor azul", 20L, categoria, new BigDecimal("120.00"));
		produtoService.cadastra(produto);

		ItemPedido itemPedido = new ItemPedido(1L, produto);
		Pedido pedido = new Pedido(aline, List.of(itemPedido));

		pedidoService.cadastrar(pedido);
	}

	@Test
	@Transient
	public void deveRetornar201AoCadastrarPedido() throws Exception {
		ItemPedidoForm itens = new ItemPedidoForm(1L, 4L);

		PedidoForm pedido = new PedidoForm(1L, List.of(itens));

		builder.cadastrar(pedido)
				.andExpect(MockMvcResultMatchers.status().is(HttpStatus.CREATED.value()));
	}

	@Test
	@Transient
	public void deveRetornar400AoCadastrarPedidoComItemDePedidoSemEstoque() throws Exception {
		ItemPedidoForm itens = new ItemPedidoForm(1L, 100L);

		PedidoForm pedido = new PedidoForm(1L, List.of(itens));
		builder.cadastrar(pedido).andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
	}
}
