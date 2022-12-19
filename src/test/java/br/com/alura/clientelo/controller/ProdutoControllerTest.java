package br.com.alura.clientelo.controller;

import br.com.alura.clientelo.api.controller.CategoriaController;
import br.com.alura.clientelo.api.controller.ProdutoController;
import br.com.alura.clientelo.api.form.CadastroProdutoForm;
import br.com.alura.clientelo.api.form.CategoriaForm;
import br.com.alura.clientelo.dto.CategoriaDto;
import br.com.alura.clientelo.dto.ProdutoDto;
import br.com.alura.clientelo.orm.StatusCategoria;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProdutoControllerTest {
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private ProdutoController produtoController;
	@Autowired
	private CategoriaController categoriaController;
	@Autowired
	private ProdutoRequestBuilder builder;

	@BeforeAll
	public void cadastraProdutoECategoria() {
		objectMapper.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true)
				.setNodeFactory(JsonNodeFactory.withExactBigDecimals(true));

		CategoriaForm artesanato = new CategoriaForm("Artesanato");
		CategoriaForm materialEscolar = new CategoriaForm("Material Escolar");

		categoriaController.cadastrar(artesanato);
		categoriaController.cadastrar(materialEscolar);

		CadastroProdutoForm cadernoForm = new CadastroProdutoForm("Caderno", new BigDecimal("50"),
				null, 20L, 1L);
		CadastroProdutoForm quadroForm = new CadastroProdutoForm("Quadro", new BigDecimal("150"),
				"Pintura de Por do Sol", 5L, 2L);

		produtoController.cadastrar(cadernoForm);
		produtoController.cadastrar(quadroForm);
	}

	@Test
	public void deveRetornar201AoCadastrarProduto() throws Exception {
		//Arrange
		CadastroProdutoForm caixaCanetas = new CadastroProdutoForm("Caixa de Canetas", new BigDecimal("30.0"),
				"caixa de canetas BIC 100 cores", 8L, 1L);
		ProdutoDto produtoDto = new ProdutoDto("Caixa de Canetas", new BigDecimal("30.0"),
				"caixa de canetas BIC 100 cores", 8L, new CategoriaDto("Artesanato", StatusCategoria.ATIVA));
		//Act
		ResultActions resultado = builder.cadastrar(caixaCanetas);

		//Assert
		resultado.andExpect(status().is(HttpStatus.CREATED.value()))
				.andExpect(content().json(objectMapper.writeValueAsString(produtoDto), false));
	}

	@Test
	public void deveListarOs3ProdutosCadastrados() throws Exception {
		//Arrange
		String respostaEsperadaJson = readJsonFile("getListaProdutos");

		//Act
		ResultActions resultado = builder.listar();

		//Assert
		resultado.andExpect(status().is(HttpStatus.OK.value()))
				.andExpect(content().json(respostaEsperadaJson, false));
	}

	@Test
	public void deveRetornar200ProdutoCadastrado() throws Exception {
		//Arrange
		String resultadoEsperado = readJsonFile("getBuscaProduto1");

		//Act
		ResultActions resultado = builder.buscar(1L);

		//Assert
		resultado.andExpect(status().is(HttpStatus.OK.value()))
				.andExpect(content().json(resultadoEsperado, false));
	}

	@Test
	public void deveRetornar404ProdutoNaoEncontrado() throws Exception {
		//Act
		ResultActions resultado = builder.buscar(1000L);

		//Assert
		resultado.andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
	}

	private String toString(Object object) throws JsonProcessingException {
		return objectMapper.writeValueAsString(object);
	}

	private String readJsonFile(String nomeArquivo) throws IOException {
		return objectMapper.readValue(this.getClass().getResourceAsStream("/".concat(nomeArquivo).concat(".json")), JsonNode.class).toString();
	}
}
