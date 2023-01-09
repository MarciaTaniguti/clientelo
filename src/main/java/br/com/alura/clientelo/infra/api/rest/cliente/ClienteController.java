package br.com.alura.clientelo.infra.api.rest.cliente;

import br.com.alura.clientelo.core.entity.cliente.Cliente;
import br.com.alura.clientelo.core.usecase.cliente.ClienteMapper;
import br.com.alura.clientelo.core.usecase.dto.ClienteDto;
import br.com.alura.clientelo.core.usecase.cliente.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/clientes")
public class ClienteController {
	@Autowired
	private ClienteService service;
	@Autowired
	private ClienteMapper mapper;

	@PostMapping
	@Transactional
	public ResponseEntity<ClienteDto> cadastrarCliente(@RequestBody @Valid ClienteForm clienteForm) {
		Cliente cliente = mapper.toModel(clienteForm);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clienteForm.id()).toUri();
		return ResponseEntity.created(uri).body(mapper.toDto(service.cadastrar(cliente)));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteDto> buscar(@PathVariable Long id) {
		return ResponseEntity.ok(mapper.toDto(service.buscaPorId(id)));
	}

	@GetMapping
	public ResponseEntity<List<ClienteDto>> buscar() {
		return ResponseEntity.ok(mapper.toDto(service.listaTodos()));
	}
}
