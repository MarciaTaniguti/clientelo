package br.com.alura.clientelo.api.controller;

import br.com.alura.clientelo.api.form.ClienteForm;
import br.com.alura.clientelo.dto.ClienteDto;
import br.com.alura.clientelo.service.CrudClienteService;
import org.apache.coyote.Response;
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
