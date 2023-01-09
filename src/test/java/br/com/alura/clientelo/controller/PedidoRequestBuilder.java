package br.com.alura.clientelo.controller;

import br.com.alura.clientelo.infra.api.rest.form.PedidoForm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@Component
public class PedidoRequestBuilder {
	@Autowired
	private MockMvc mockMvc;
	private final URI uriPedidos;
	@Autowired
	private ObjectMapper objectMapper;

	public PedidoRequestBuilder() throws URISyntaxException {
		uriPedidos = new URI("/api/pedidos");
	}

	ResultActions cadastrar(PedidoForm pedido) throws Exception {
		String pedidoJson = objectMapper.writeValueAsString(pedido);
		return mockMvc.perform(post(uriPedidos)
				.contentType(MediaType.APPLICATION_JSON)
				.content(pedidoJson));
	}

	ResultActions listar() throws Exception {
		return mockMvc.perform(get(uriPedidos)
				.accept(MediaType.APPLICATION_JSON_VALUE));
	}

	ResultActions buscar(Long id) throws Exception {
		return mockMvc.perform(get(uriPedidos + "/" + id)
				.accept(MediaType.APPLICATION_JSON_VALUE));
	}
}
