package br.com.alura.clientelo.api.controller;

import br.com.alura.clientelo.api.form.ItemPedidoForm;
import br.com.alura.clientelo.api.form.PedidoForm;
import br.com.alura.clientelo.api.mapper.PedidoMapper;
import br.com.alura.clientelo.orm.ItemPedido;
import br.com.alura.clientelo.orm.Pedido;
import br.com.alura.clientelo.service.CrudItemPedidoService;
import br.com.alura.clientelo.service.CrudPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/api/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController {
	@Autowired
	private CrudPedidoService pedidoService;
	@Autowired
	private PedidoMapper mapper;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PedidoForm> cadastrar(@RequestBody @Valid PedidoForm pedidoForm) {
		PedidoForm pedidoCriado = pedidoService.cadastrar(pedidoForm);
		return ResponseEntity.status(HttpStatus.CREATED).body(pedidoCriado);
	}
}
