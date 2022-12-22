package br.com.alura.clientelo.infra.api.rest.cliente;

import br.com.alura.clientelo.api.form.ClienteForm;
import br.com.alura.clientelo.core.entity.cliente.Cliente;
import br.com.alura.clientelo.core.usecase.dto.ClienteDto;
import br.com.alura.clientelo.core.usecase.cliente.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/clientes")
public class ClienteController {
	@Autowired
	private ClienteService service;

	@PostMapping
	@Transactional
	public ResponseEntity<ClienteDto> cadastrarCliente(@RequestBody @Valid ClienteForm clienteForm) {
		ClienteDto cliente = service.cadastrar(clienteForm);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clienteForm.id()).toUri();
		return ResponseEntity.created(uri).body(cliente);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteDto> buscar(@PathVariable Long id) {
		ClienteDto cliente = service.buscaPorId(id);
		return ResponseEntity.ok(cliente);
	}

	@GetMapping
	public ResponseEntity<List<ClienteDto>> buscar() {
		List<ClienteDto> clientes = service.listaTodos();
		return ResponseEntity.ok(clientes);
	}
}
