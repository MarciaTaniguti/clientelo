package br.com.alura.clientelo.api.controller;

import br.com.alura.clientelo.api.form.DetalhePedidoForm;
import br.com.alura.clientelo.api.form.PedidoForm;
import br.com.alura.clientelo.api.mapper.PedidoMapper;
import br.com.alura.clientelo.service.CrudPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

	@GetMapping(path = "/{id}")
	public ResponseEntity<DetalhePedidoForm> exibirDetalhes(@PathVariable Long id) {
		DetalhePedidoForm detalhePedidoForm = pedidoService.buscarDetalhePedido(id);
		return ResponseEntity.status(HttpStatus.OK).body(detalhePedidoForm);
	}
}
