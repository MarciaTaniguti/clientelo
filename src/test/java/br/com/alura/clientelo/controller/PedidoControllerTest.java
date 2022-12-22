package br.com.alura.clientelo.controller;

import br.com.alura.clientelo.api.form.CategoriaForm;
import br.com.alura.clientelo.core.entity.categoria.Categoria;
import br.com.alura.clientelo.core.entity.categoria.StatusCategoria;
import br.com.alura.clientelo.core.usecase.produto.ProdutoService;
import br.com.alura.clientelo.infra.api.rest.PedidoController;
import br.com.alura.clientelo.api.form.ItemPedidoForm;
import br.com.alura.clientelo.api.form.PedidoForm;
import br.com.alura.clientelo.core.entity.cliente.Cliente;
import br.com.alura.clientelo.core.entity.cliente.Endereco;
import br.com.alura.clientelo.core.entity.pedido.item.ItemPedido;
import br.com.alura.clientelo.core.entity.pedido.Pedido;
import br.com.alura.clientelo.core.entity.produto.Produto;
import br.com.alura.clientelo.core.usecase.categoria.CategoriaService;
import br.com.alura.clientelo.core.usecase.cliente.ClienteService;
import br.com.alura.clientelo.core.usecase.cliente.EnderecoService;
import br.com.alura.clientelo.core.usecase.pedido.CrudPedidoService;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
	private ProdutoService produtoService;
	@Autowired
	private PedidoController pedidoController;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private CrudPedidoService pedidoService;
	@Autowired
	private EnderecoService enderecoService;
	@Autowired
	private CategoriaService categoriaService;
	@Autowired
	private PedidoRequestBuilder builder;

	@BeforeAll
	public void setUp() {
		Categoria categoria = new Categoria("Decoração");
		categoriaService.cadastrar(categoria);

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
	public void deveRetornar201AoCadastrarPedido() throws Exception {
		ItemPedidoForm itens = new ItemPedidoForm(1L, 4L);

		PedidoForm pedido = new PedidoForm(1L, List.of(itens));

		builder.cadastrar(pedido)
				.andExpect(MockMvcResultMatchers.status().is(HttpStatus.CREATED.value()));
	}

	@Test
	public void deveRetornar400AoCadastrarPedidoComItemDePedidoSemEstoque() throws Exception {
		ItemPedidoForm itens = new ItemPedidoForm(1L, 100L);

		PedidoForm pedido = new PedidoForm(1L, List.of(itens));
		builder.cadastrar(pedido).andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
	}
}
