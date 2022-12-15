package br.com.alura.clientelo.controller;

import br.com.alura.clientelo.api.form.CadastroProdutoForm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@Component
public class ProdutoRequestBuilder {
	@Autowired
	private MockMvc mockMvc;
	private final URI uriProdutos;
	@Autowired
	private ObjectMapper objectMapper;

	public ProdutoRequestBuilder() throws URISyntaxException {
		uriProdutos = new URI("/api/produtos");
	}

	ResultActions cadastrar(CadastroProdutoForm produto) throws Exception {
		String produtoJson = objectMapper.writeValueAsString(produto);
		return mockMvc.perform(post(uriProdutos)
				.contentType(MediaType.APPLICATION_JSON)
				.content(produtoJson));
	}

	ResultActions listar() throws Exception {
		return mockMvc.perform(get(uriProdutos)
				.accept(MediaType.APPLICATION_JSON_VALUE));
	}

	ResultActions buscar(Long id) throws Exception {
		return mockMvc.perform(get(uriProdutos + "/" + id)
				.accept(MediaType.APPLICATION_JSON_VALUE));
	}
}
