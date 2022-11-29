package br.com.alura.clientelo.api.controller;

import br.com.alura.clientelo.api.dto.CategoriaDto;
import br.com.alura.clientelo.service.CrudCategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = {"/api/categoria"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoriaController {
	private final CrudCategoriaService categoriaService;

	@Autowired
	public CategoriaController(CrudCategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity cadastrar(@RequestBody @Valid CategoriaDto categoria) {
		CategoriaDto categoriaCriada = categoriaService.cadastra(categoria);
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaCriada);
	}
}
