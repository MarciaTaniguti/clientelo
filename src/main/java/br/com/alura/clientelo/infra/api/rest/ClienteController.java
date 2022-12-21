package br.com.alura.clientelo.infra.api.rest;

import br.com.alura.clientelo.api.form.ClienteForm;
import br.com.alura.clientelo.core.usecase.dto.ClienteDto;
import br.com.alura.clientelo.core.usecase.cliente.CrudClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/clientes")
public class ClienteController {
	@Autowired
	private CrudClienteService service;
	//public ResponseEntity<PedidoDto> cadastrar(@RequestBody @Valid PedidoForm pedidoForm) {

	@PostMapping
	@Transactional
	public ResponseEntity<ClienteDto> cadastrarCliente(@RequestBody @Valid ClienteForm clienteForm) {
//		return ResponseEntity.created()
		ClienteDto cliente = service.cadastrar(clienteForm);
		return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
	}
}
