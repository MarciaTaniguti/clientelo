package br.com.alura.clientelo.infra.api.rest;

import br.com.alura.clientelo.core.entity.pedido.Pedido;
import br.com.alura.clientelo.infra.api.rest.form.DetalhePedidoForm;
import br.com.alura.clientelo.infra.api.rest.form.PedidoForm;
import br.com.alura.clientelo.core.usecase.pedido.PedidoMapper;
import br.com.alura.clientelo.core.usecase.dto.PedidoDto;
import br.com.alura.clientelo.core.usecase.pedido.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController {
	@Autowired
	private PedidoService pedidoService;
	@Autowired
	private PedidoMapper mapper;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PedidoDto> cadastrar(@RequestBody @Valid PedidoForm pedidoForm) {
		Pedido pedido = mapper.toModel(pedidoForm);
		pedidoService.cadastrar(pedido);
		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(pedido.getId())
				.toUri())
				.body(mapper.toDto(pedido));
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<DetalhePedidoForm> exibirDetalhes(@PathVariable Long id) {
		Pedido pedido = pedidoService.buscarDetalhePedido(id);
		DetalhePedidoForm detalhePedidoForm = mapper.toDetalhePedidoForm(pedido);
		detalhePedidoForm.setDescontos(pedido.getValorDesconto());
		return ResponseEntity.ok(detalhePedidoForm);
	}

	@GetMapping
	public ResponseEntity<List<PedidoDto>> listarTodos() {
		List<Pedido> detalhePedidoForm = pedidoService.listarTodos();
		return ResponseEntity.ok(mapper.toDto(detalhePedidoForm));
	}
}
